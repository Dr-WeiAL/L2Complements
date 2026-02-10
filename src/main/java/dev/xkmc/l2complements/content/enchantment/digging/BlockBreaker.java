package dev.xkmc.l2complements.content.enchantment.digging;

import dev.xkmc.l2complements.content.enchantment.core.CraftableEnchantment;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.Set;

public interface BlockBreaker {

	BlockBreakerInstance getInstance(DiggerContext ctx);

	int getMaxLevel();

	default boolean ignoreHardness() {
		return false;
	}

	List<Component> descFull(int lv, String key, boolean alt, boolean book);

	default Set<Integer> getCraftableLevels() {
		return CraftableEnchantment.DEF;
	}

}
