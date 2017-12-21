/*******************************************************************************
 * Copyright (c) 2017 ZCaliptium.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package tws.zcaliptium.knucklepay.common.handlers;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonEventHandler
{
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {}
	
	@SubscribeEvent
	public void onWorldUnload(WorldEvent.Unload event){}
	
	@SubscribeEvent
	public void onWorldSave(WorldEvent.Save event) {}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {}
}
