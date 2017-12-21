package tws.zcaliptium.knucklepay.common.handlers;

import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.economy.EconomyTransactionEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.Optional;
import tws.zcaliptium.knucklepay.common.KnucklePay;
import tws.zcaliptium.knucklepay.common.SpongeBridge;

@Optional.Interface(iface = "org.spongepowered.api.event.EventListener", modid = SpongeBridge.MODID)
public class SpongeTransactionListener implements EventListener<EconomyTransactionEvent>
{
    @Optional.Method(modid = SpongeBridge.MODID)
    @Override
    public void handle(EconomyTransactionEvent event) throws Exception
    {
    	// NOTE: Event handler processed in different thread than main.
    	//KnucklePay.modLog.info("Catched transaction: " + event.toString());

        IThreadListener mainThread = KnucklePay.proxy.getMainThreadListener();
        mainThread.addScheduledTask(new Runnable() {
            @Override
            public void run() {
            	// TODO: Balance update packet send here.
            	KnucklePay.modLog.info("Catched transaction: " + event.toString());
            }
        });
    }
}
