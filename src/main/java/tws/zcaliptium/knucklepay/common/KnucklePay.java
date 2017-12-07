/*******************************************************************************
 * Copyright (c) 2017 ZCaliptium.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package tws.zcaliptium.knucklepay.common;

import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.MODID, name = ModInfo.MODNAME, dependencies="after:spongeforge", version = ModInfo.VERSION)
public class KnucklePay
{   
	@Instance(ModInfo.MODID)
	public static KnucklePay instance;
	
	@SidedProxy(clientSide = "tws.zcaliptium.knucklepay.client.ClientProxy", serverSide = "tws.zcaliptium.knucklepay.common.CommonProxy")
	public static CommonProxy proxy;

	public static Logger modLog;
	
    @EventHandler
    public void load(FMLPreInitializationEvent event)
	{
    	modLog = event.getModLog();
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @EventHandler
    public void afterModsLoaded(FMLPostInitializationEvent event)
    {
    }
}
