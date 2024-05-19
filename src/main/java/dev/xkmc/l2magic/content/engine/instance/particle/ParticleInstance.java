package dev.xkmc.l2magic.content.engine.instance.particle;

import dev.xkmc.l2magic.content.engine.core.EngineConfiguration;
import dev.xkmc.l2magic.content.engine.core.EngineContext;
import dev.xkmc.l2magic.content.engine.variable.DoubleVariable;
import net.minecraft.core.particles.ParticleOptions;

import javax.annotation.Nullable;

public interface ParticleInstance<T extends Record & ParticleInstance<T>> extends EngineConfiguration<T> {

	@Nullable
	ParticleOptions particle(EngineContext ctx);

	DoubleVariable speed();

	@Override
	default void execute(EngineContext ctx) {
		if (!ctx.user().level().isClientSide()) return;
		var opt = particle(ctx);
		if (opt == null) return;
		var pos = ctx.loc().pos();
		var vec = ctx.loc().dir().scale(speed().eval(ctx));
		ctx.user().level().addParticle(opt, true, pos.x, pos.y, pos.z, vec.x, vec.y, vec.z);
	}

}
