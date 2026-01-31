package dev.xkmc.l2complements.content.enchantment.digging;

import dev.xkmc.l2complements.init.data.LCLang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.List;

public record EchoDigger(int r, int max) implements BlockBreaker {

	@Override
	public BlockBreakerInstance getInstance(DiggerContext ctx) {
		return new EchoInstance(r << (ctx.level() - 1), max << (ctx.level() - 1), ctx.state().getBlock());
	}

	@Override
	public List<Component> descFull(int lv, String key, boolean alt, boolean book) {
		return List.of(Component.translatable(key,
								(r << (Math.min(getMaxLevel(), lv) - 1)) + "",
								(max << (Math.min(getMaxLevel(), lv) - 1)) + "")
						.withStyle(ChatFormatting.GRAY),
				LCLang.diggerRotate().withStyle(ChatFormatting.DARK_GRAY)
		);
	}
	@Override
	public int getMaxLevel() {
		return 3;
	}

}
