package com.yyon.grapplinghook.items;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.yyon.grapplinghook.grapplemod;
import com.yyon.grapplinghook.entities.enderArrow;
import com.yyon.grapplinghook.entities.grappleArrow;

/*
 * This file is part of GrappleMod.

    GrappleMod is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    GrappleMod is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with GrappleMod.  If not, see <http://www.gnu.org/licenses/>.
 */

public class enderBow extends grappleBow implements clickitem {
	
	public enderBow() {
		super();
		setUnlocalizedName("enderhook");
	}
	
	@Override
	public grappleArrow createarrow(ItemStack stack, World worldIn, EntityLivingBase playerIn, boolean righthand) {
		return new enderArrow(worldIn, playerIn, righthand);
	}
	
	@Override
	public void onLeftClick(ItemStack stack, EntityPlayer player) {
		if (player.worldObj.isRemote) {
			grapplemod.proxy.launchplayer(player);
		}
	}
	@Override
	public void onLeftClickRelease(ItemStack stack, EntityPlayer player) {
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		list.add("A grappling hook which uses an ender staff to speed up or change directions");
		list.add("");
		list.add(grapplemod.getkeyname(minecraft.gameSettings.keyBindUseItem) + " - Throw grappling hook");
		list.add(grapplemod.getkeyname(minecraft.gameSettings.keyBindUseItem) + " again - Release");
		list.add("Double-" + grapplemod.getkeyname(minecraft.gameSettings.keyBindUseItem) + " - Release and throw again");
		list.add(grapplemod.getkeyname(minecraft.gameSettings.keyBindAttack) + " - Launch player towards crosshairs");
		list.add(grapplemod.getkeyname(minecraft.gameSettings.keyBindForward) + ", " +
				grapplemod.getkeyname(minecraft.gameSettings.keyBindLeft) + ", " +
				grapplemod.getkeyname(minecraft.gameSettings.keyBindBack) + ", " +
				grapplemod.getkeyname(minecraft.gameSettings.keyBindRight) +
				" - Swing");
		list.add(grapplemod.getkeyname(minecraft.gameSettings.keyBindJump) + " - Release and jump (while in midair)");
		list.add(grapplemod.getkeyname(minecraft.gameSettings.keyBindSneak) + " - Stop swinging");
		list.add(grapplemod.getkeyname(minecraft.gameSettings.keyBindSneak) + " + " +
				grapplemod.getkeyname(minecraft.gameSettings.keyBindForward) + 
				" - Climb up");
		list.add(grapplemod.getkeyname(minecraft.gameSettings.keyBindSneak) + " + " +
				grapplemod.getkeyname(minecraft.gameSettings.keyBindBack) + 
				" - Climb down");
	}
}
