/*******************************************************************************
 * Copyright (c) 2017 ZCaliptium.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package tws.zcaliptium.knucklepay.common.network;

import java.util.UUID;

import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.service.economy.account.UniqueAccount;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tws.zcaliptium.knucklepay.client.hud.BalanceHudOverlayHandler;
import tws.zcaliptium.knucklepay.common.KnucklePay;

public class MsgSyncBalance implements IMessage, IMessageHandler<MsgSyncBalance, IMessage>
{
	protected String currencyID;
	protected String balanceString;
	
	public MsgSyncBalance() {}
	
	public MsgSyncBalance(String currencyID, String balanceString) {
		this.currencyID = currencyID;
		this.balanceString = balanceString;
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, currencyID);
		ByteBufUtils.writeUTF8String(buffer, balanceString);
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		currencyID = ByteBufUtils.readUTF8String(buffer);
		balanceString = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(MsgSyncBalance message, MessageContext ctx)
	{
        IThreadListener mainThread = KnucklePay.proxy.getMainThreadListener();

        mainThread.addScheduledTask(new Runnable() {
            @Override
            public void run() {
            	//KnucklePay.modLog.info("MsgSyncBalance ['" + message.currencyID + "', '" + message.balanceString + "']");
            	
            	// TODO: You can put HUD update here.
            }
        });
		
		return null;
	}
}
