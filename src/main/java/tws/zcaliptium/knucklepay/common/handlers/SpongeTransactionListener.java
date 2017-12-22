package tws.zcaliptium.knucklepay.common.handlers;

import java.util.UUID;

import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.economy.EconomyTransactionEvent;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.service.economy.transaction.ResultType;
import org.spongepowered.api.service.economy.transaction.TransactionResult;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.Optional;
import tws.zcaliptium.knucklepay.common.KnucklePay;
import tws.zcaliptium.knucklepay.common.SpongeBridge;
import tws.zcaliptium.knucklepay.common.network.MsgSyncBalance;
import tws.zcaliptium.knucklepay.common.network.PacketHandler;

@Optional.Interface(iface = "org.spongepowered.api.event.EventListener", modid = SpongeBridge.MODID)
public class SpongeTransactionListener implements EventListener<EconomyTransactionEvent>
{
    @Optional.Method(modid = SpongeBridge.MODID)
    @Override
    public void handle(EconomyTransactionEvent event) throws Exception
    {
    	// NOTE: Event handler processed in different thread than main.
    	//KnucklePay.modLog.info("Catched transaction: " + event.toString());
    	
    	TransactionResult transactionResult = event.getTransactionResult();
    	
    	// Handle only successful transactions. And with default currency.
    	if (transactionResult.getResult().equals(ResultType.SUCCESS))
    	{
            IThreadListener mainThread = KnucklePay.proxy.getMainThreadListener();

            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                	//KnucklePay.modLog.info("Catched transaction: " + event.toString());
                	
                	// TODO: Rewrite it in future to use together with mod-own accounts system.
                	
                	Account account = transactionResult.getAccount();
                	
                	if (account != null && account instanceof UniqueAccount)
                	{
                		UUID accountID = ((UniqueAccount)account).getUniqueId();
                		EntityPlayerMP ownerPlayerMP = KnucklePay.minecraftServer.getPlayerList().getPlayerByUUID(accountID);
                		
                		if (ownerPlayerMP != null) {
                    		String balanceString = account.getBalance(transactionResult.getCurrency()).toPlainString();
                    		//KnucklePay.modLog.info("onServer: " + balanceString);
                    		PacketHandler.INSTANCE.sendTo(new MsgSyncBalance(transactionResult.getCurrency().getId(), balanceString), ownerPlayerMP);
                		}
                	}
                }
            });
    	}
    }
}
