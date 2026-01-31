package dev.xkmc.l2complements.content.enchantment.digging;

public record EchoDigger(int r) implements SimpleNumberDesc {

	@Override
	public BlockBreakerInstance getInstance(DiggerContext ctx) {
		return new EchoInstance(r << (ctx.level() - 1), ctx.state().getBlock());
	}

	@Override
	public int range(int lv) {
		return r << (lv - 1);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

}
