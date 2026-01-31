package dev.xkmc.l2complements.content.enchantment.digging;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public record EchoInstance(int r, int max, Block block) implements BlockBreakerInstance {

	@Override
	public List<BlockPos> find(Level level, BlockPos pos, Predicate<BlockPos> pred) {
		List<BlockPos> list = new ArrayList<>();
		for (int x = -r; x <= r; x++) {
			for (int y = -r; y <= r; y++) {
				for (int z = -r; z <= r; z++) {
					if (x * x + y * y + z * z > r * r) continue;
					BlockPos i = pos.offset(x, y, z);
					if (level.getBlockState(i).getBlock() == block && pred.test(i)) {
						list.add(i);
					}
				}
			}
		}
		list.sort(Comparator.comparingInt(a -> (int) a.distSqr(pos)));
		if (list.size() > max) list = list.subList(0, max);
		return list;
	}

}
