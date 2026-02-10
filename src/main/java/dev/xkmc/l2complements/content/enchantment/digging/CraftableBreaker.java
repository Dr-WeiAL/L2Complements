package dev.xkmc.l2complements.content.enchantment.digging;

import java.util.Set;

public interface CraftableBreaker extends SimpleNumberDesc {

	@Override
	default int getMaxLevel() {
		return 3;
	}

	@Override
	default Set<Integer> getCraftableLevels() {
		return Set.of(1, 2, 3);
	}

}
