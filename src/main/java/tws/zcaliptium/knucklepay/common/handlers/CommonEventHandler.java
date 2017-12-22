/*******************************************************************************
 * Copyright (c) 2017 ZCaliptium.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package tws.zcaliptium.knucklepay.common.handlers;

import java.util.Optional;
import java.util.UUID;

import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.service.economy.account.UniqueAccount;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tws.zcaliptium.knucklepay.common.KnucklePay;
import tws.zcaliptium.knucklepay.common.SpongeBridge;
import tws.zcaliptium.knucklepay.common.network.MsgSyncBalance;
import tws.zcaliptium.knucklepay.common.network.PacketHandler;

public class CommonEventHandler
{
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		Entity entity = event.getEntity();
		
		if (entity instanceof EntityPlayer && entity.world.isRemote)
		{
			if (Loader.isModLoaded(SpongeBridge.MODID))
			{
				// TODO: Here is ugly code. It is better code own accounts system which will wrap sponge stuff.
				
				UUID accountID = entity.getUniqueID();
				Optional<UniqueAccount> spongeAccount = SpongeBridge.economyService.getOrCreateAccount(accountID);
				
				if (spongeAccount.isPresent()) {
					String balanceString = spongeAccount.get().getBalance(SpongeBridge.economyService.getDefaultCurrency()).toPlainString();
					EntityPlayerMP entityPlayerMP = KnucklePay.minecraftServer.getPlayerList().getPlayerByUUID(accountID);
					
					if (entityPlayerMP == null) {
						return;
					}
					
					PacketHandler.INSTANCE.sendTo(new MsgSyncBalance(SpongeBridge.economyService.getDefaultCurrency().getId(), balanceString), entityPlayerMP);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onWorldUnload(WorldEvent.Unload event){}
	
	@SubscribeEvent
	public void onWorldSave(WorldEvent.Save event) {}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {}
}
