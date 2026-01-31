package dev.xkmc.l2complements.content.item.misc;

import dev.xkmc.l2complements.content.client.SpeedTrackerData;
import dev.xkmc.l2complements.content.client.SpeedTrackerPacket;
import dev.xkmc.l2complements.init.L2Complements;
import dev.xkmc.l2complements.init.data.LCConfig;
import dev.xkmc.l2complements.init.registrate.LCItems;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class WindBottle extends TooltipItem {

	public WindBottle(Properties properties, Supplier<MutableComponent> sup) {
		super(properties, sup);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean select) {
		if (entity instanceof ServerPlayer player && !player.onGround()) {
			var speed = entity.getDeltaMovement().length();
			var time = player.level().getGameTime();
			var prev = player.getPersistentData().getLong("LastWindBottleTime");
			if (prev != time) {
				L2Complements.HANDLER.toClientPlayer(new SpeedTrackerPacket(time, speed), player);
				player.getPersistentData().putLong("LastWindBottleTime", time);
			}
			if (speed >= LCConfig.SERVER.windSpeed.get()) {
				stack.shrink(1);
				player.getInventory().placeItemBackInInventory(LCItems.CAPTURED_WIND.asStack());
			}
		}
	}

	@Override
	public boolean isBarVisible(ItemStack stack) {
		return SpeedTrackerData.getSpeed() > 0;
	}

	@Override
	public int getBarWidth(ItemStack stack) {
		var perc = SpeedTrackerData.getSpeed() / LCConfig.SERVER.windSpeed.get();
		return Math.min(13, (int) (perc * 13f));
	}

	@Override
	public int getBarColor(ItemStack stack) {
		return 0xffffffff;
	}


}
