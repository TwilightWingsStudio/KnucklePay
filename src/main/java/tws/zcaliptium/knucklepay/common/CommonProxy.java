/*******************************************************************************
 * Copyright (c) 2017 ZCaliptium.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package tws.zcaliptium.knucklepay.common;

import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tws.zcaliptium.knucklepay.common.handlers.CommonEventHandler;
import tws.zcaliptium.knucklepay.fex.StartupCommon;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
    }
	
	public void afterModsLoaded(FMLPostInitializationEvent event)
	{
	}
	
	public boolean isClient() {
		return false;
	}

	public void registerEventHandlers()
	{
	}
	
	public IThreadListener getMainThreadListener() {
		return FMLCommonHandler.instance().getSidedDelegate().getServer();
	}
}
