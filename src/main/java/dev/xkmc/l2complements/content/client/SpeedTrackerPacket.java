package dev.xkmc.l2complements.content.client;

import dev.xkmc.l2serial.network.SerialPacketBase;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraftforge.network.NetworkEvent;

@SerialClass
public class SpeedTrackerPacket extends SerialPacketBase {

	public static SpeedTrackerPacket of(long time, double speed) {
		var ans = new SpeedTrackerPacket();
		ans.speed = speed;
		ans.time = time;
		return ans;
	}

	@SerialClass.SerialField
	public double speed;

	@SerialClass.SerialField
	public long time;

	@Override
	public void handle(NetworkEvent.Context context) {
		SpeedTrackerData.update(time, speed);
	}

}
