package dev.xkmc.l2magic.content.engine.core;

import net.minecraft.world.phys.Vec3;

public record LocationContext(Vec3 pos, Vec3 dir, Vec3 normal) {

	public static final Vec3 UP = new Vec3(0, 1, 0);

	public static LocationContext of(Vec3 pos, Vec3 dir) {
		return of(pos, dir, UP);
	}

	public static LocationContext of(Vec3 pos, Vec3 dir, Vec3 normal) {
		return new LocationContext(pos, dir, normal);
	}

	public LocationContext with(Vec3 pos) {
		return new LocationContext(pos, dir, normal);
	}

	public LocationContext add(Vec3 offset) {
		return with(pos.add(offset));
	}

	public LocationContext rotateDegree(double degree) {
		return new LocationContext(pos, Orientation.getOrientation(dir, normal).rotateDegrees(degree), normal);
	}
}
