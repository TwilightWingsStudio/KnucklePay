/*******************************************************************************
 * Copyright (c) 2017 ZCaliptium.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package tws.zcaliptium.knucklepay.client.hud;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import tws.zcaliptium.knucklepay.common.ModInfo;
import tws.zcaliptium.knucklepay.common.SpongeBridge;

import java.math.BigDecimal;
import java.util.Optional;

import javax.vecmath.Point2i;

import org.lwjgl.opengl.GL11;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.account.UniqueAccount;

@SideOnly(Side.CLIENT)
public class BalanceHudOverlayHandler
{
	private static final ResourceLocation modIcons = new ResourceLocation(ModInfo.MODID, "textures/icons.png");
	public static String balanceString = "---";
	
    private int countYOffset(EntityPlayerSP p, boolean left, boolean right) {
        if (p == null) return 0;
        PlayerCapabilities pc = p.capabilities;
        boolean up = (right && p.isInsideOfMaterial(Material.WATER) && !p.canBreatheUnderwater()) ||
                (left && p.getTotalArmorValue() > 0);
        return pc.isCreativeMode ? 0 : 17 + (up ? 10 : 0);
    }

    private void drawBalance()
    {
		Minecraft mc = Minecraft.getMinecraft();
    	
        mc.mcProfiler.startSection("balancedraw");
        ScaledResolution resolution = new ScaledResolution(mc);
        FontRenderer fontRenderer = mc.getRenderManager().getFontRenderer();
        if (fontRenderer == null) return;
        
        String amountString = balanceString;
        
        // If bad things happen.
        if (amountString == null) {
        	amountString = TextFormatting.RED + "error";
        }

        String text = fontRenderer.listFormattedStringToWidth(amountString, 64).get(0);

        mc.getTextureManager().bindTexture(modIcons);

        Point2i position = new Point2i(resolution.getScaledWidth() / 2 + 91, resolution.getScaledHeight() - 33 - countYOffset(mc.player, false, true));//EnderPay.settings.getPosition().getPoint(resolution, mc);
        int x = position.getX();
        int y = position.getY();

        int textLength = fontRenderer.getStringWidth(text);
        x -= (textLength + 18) / 1;

        y -= 4;
        
        mc.ingameGUI.drawTexturedModalRect(x, y - 2, 0, 0, 16, 16);
        mc.fontRenderer.drawString(text, x + 18, y + 2, 0xa0a0a0, true);

        mc.mcProfiler.endSection();
}

	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onRender(RenderGameOverlayEvent.Post event)
	{
        if (event.isCancelable()) return;
        if (event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE &&
                event.getType() != RenderGameOverlayEvent.ElementType.HEALTHMOUNT)
            return;
        
        Minecraft mc = Minecraft.getMinecraft();

        if (mc == null || mc.player == null || mc.world == null || mc.player.isSpectator()) return;
		
        drawBalance();
	}
}
