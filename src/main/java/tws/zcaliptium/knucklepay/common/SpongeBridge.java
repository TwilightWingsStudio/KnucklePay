/*******************************************************************************
 * Copyright (c) 2017 ZCaliptium.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package tws.zcaliptium.knucklepay.common;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.economy.EconomyService;

import net.minecraftforge.fml.common.Optional;

public class SpongeBridge
{
	public static final String MODID = "sponge";
	public static boolean hasSponge = false;
	public static EconomyService economyService;
	
    @Optional.Method(modid = MODID)
    public static void economyInit()
    {
    	java.util.Optional<EconomyService> optionalEconomyService = Sponge.getServiceManager().provide(EconomyService.class);
    	
    	if (optionalEconomyService.isPresent()) {
    		KnucklePay.modLog.info("Detected the Sponge economy plugin!");
    		economyService = optionalEconomyService.get();
    	} else {
    		KnucklePay.modLog.warn("There is no any economy Sponge plugin!");
    	}
    }
}
