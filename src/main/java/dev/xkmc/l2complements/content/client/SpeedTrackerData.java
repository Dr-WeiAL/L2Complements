package dev.xkmc.l2complements.content.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class SpeedTrackerData {

	public static long time;
	public static double speed;
	public static Player player;

	public static void update(long t, double s) {
		player = Minecraft.getInstance().player;
		if (player == null) {
			time = -1;
			speed = 0;
			return;
		}
		time = t;
		speed = s;
	}

	public static double getSpeed() {
		var pl = Minecraft.getInstance().player;
		if (pl == null || pl != player) return 0;
		long t = pl.level().getGameTime();
		if (t + 20 < time || t > time + 20) return 0;
		if (pl.onGround()) return 0;
		return speed;
	}

}
