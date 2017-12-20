package tws.zcaliptium.knucklepay.common.command;

import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import tws.zcaliptium.knucklepay.common.SpongeBridge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandStatus extends CommandBase
{
    @Override
    @Nonnull
    public String getName() {
        return "kpstatus";
    }

    @Override
    @Nonnull
    public String getUsage(@Nullable ICommandSender sender) {
        return "commands.kpstatus.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
    	if (sender != null) {
    		boolean hasSponge = SpongeBridge.hasSponge;
    		
    		sender.sendMessage(new TextComponentString(
    				"[KnucklePay] Status:" +
    				"\n  SpongeForge: " + (hasSponge ? "Yes" : "No") +
    				"\n  Economy Plugin: " + (SpongeBridge.economyService != null ? "Yes" : "No") +
    				"\n  Currencies Count: " + (SpongeBridge.economyService != null ? SpongeBridge.economyService.getCurrencies().size() : 0)
    				));
    	}
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }

    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
