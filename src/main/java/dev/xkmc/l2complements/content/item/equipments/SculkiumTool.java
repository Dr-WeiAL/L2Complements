package dev.xkmc.l2complements.content.item.equipments;

import dev.xkmc.l2complements.init.data.LCLang;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class SculkiumTool extends ExtraToolConfig {

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state, float old) {
		var destroySpeed = state.destroySpeed;
		return destroySpeed > 1 ? old * destroySpeed : old;
	}

	@Override
	public void addTooltip(ItemStack stack, List<Component> list) {
		list.add(LCLang.IDS.SCULKIUM_TOOL.get().withStyle(ChatFormatting.GRAY));
	}

}
