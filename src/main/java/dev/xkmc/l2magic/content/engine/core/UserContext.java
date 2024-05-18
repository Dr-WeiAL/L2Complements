package dev.xkmc.l2magic.content.engine.core;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public record UserContext(Level level, LivingEntity user, RandomSource rand) {
}
