/*******************************************************************************
 * Copyright (c) 2017 ZCaliptium.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package tws.zcaliptium.knucklepay.client;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tws.zcaliptium.knucklepay.client.hud.BalanceHudOverlayHandler;
import tws.zcaliptium.knucklepay.common.CommonProxy;
import tws.zcaliptium.knucklepay.fex.StartupClientOnly;
import tws.zcaliptium.knucklepay.fex.StartupCommon;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
	}
	
	@Override
    public void preInit(FMLPreInitializationEvent event)
    {
    	super.preInit(event);
    }
	
	@Override
	public void afterModsLoaded(FMLPostInitializationEvent event)
	{
		super.afterModsLoaded(event);
	}
	
	@Override
	public boolean isClient() {
		return true;
	}
}
