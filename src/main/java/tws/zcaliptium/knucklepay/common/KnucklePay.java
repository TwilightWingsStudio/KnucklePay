/*******************************************************************************
 * Copyright (c) 2017 ZCaliptium.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package tws.zcaliptium.knucklepay.common;

import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.economy.EconomyService;

import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import tws.zcaliptium.knucklepay.common.command.CommandStatus;

@Mod(modid = ModInfo.MODID, name = ModInfo.MODNAME, dependencies="after:sponge", version = ModInfo.VERSION)
public class KnucklePay
{   
	@Instance(ModInfo.MODID)
	public static KnucklePay instance;
	
	@SidedProxy(clientSide = "tws.zcaliptium.knucklepay.client.ClientProxy", serverSide = "tws.zcaliptium.knucklepay.common.CommonProxy")
	public static CommonProxy proxy;

	public static Logger modLog;
	
	public static MinecraftServer minecraftServer; // Access to running server from all parts of mod.
	
    @EventHandler
    public void load(FMLPreInitializationEvent event)
	{
    	modLog = event.getModLog();
    	
    	proxy.preInit(event);
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.registerEventHandlers();
    }
    
    @EventHandler
    public void afterModsLoaded(FMLPostInitializationEvent event)
    {
    	if (Loader.isModLoaded(SpongeBridge.MODID)) {
    		SpongeBridge.hasSponge = true;
        	SpongeBridge.economyInit();
    	} else {
    		modLog.warn("There is no SpongeForge installed!");
    	}
    	
    	proxy.afterModsLoaded(event);
    }
    
    @EventHandler
	public void serverLoad(FMLServerStartingEvent event)
    {
    	minecraftServer = event.getServer();
    	
		event.registerServerCommand(new CommandStatus());
	}
}
