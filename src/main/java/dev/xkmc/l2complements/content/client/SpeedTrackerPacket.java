package dev.xkmc.l2complements.content.client;

import dev.xkmc.l2serial.network.SerialPacketBase;
import net.minecraft.world.entity.player.Player;

public record SpeedTrackerPacket(long time, double speed) implements SerialPacketBase<SpeedTrackerPacket> {

	@Override
	public void handle(Player player) {
		SpeedTrackerData.update(time, speed);
	}

}
