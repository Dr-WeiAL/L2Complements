package dev.xkmc.l2complements.content.enchantment.legacy;

import dev.xkmc.l2core.base.effects.EffectUtil;
import dev.xkmc.l2core.init.reg.ench.CustomDescEnchantment;
import dev.xkmc.l2core.init.reg.ench.EnchColor;
import dev.xkmc.l2core.init.reg.ench.LegacyEnchantment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public abstract class AbstractThornEnchantment extends LegacyEnchantment implements CustomDescEnchantment {

	public void onDamage(LivingEntity attacker, LivingEntity wearer, int level) {
		if (wearer != attacker && !attacker.level().isClientSide())
			EffectUtil.addEffect(attacker, getEffect(level), wearer);
	}

	protected abstract MobEffectInstance getEffect(int pLevel);

	@Override
	public List<Component> descFull(int lv, String key, boolean alt, boolean book, EnchColor color) {
		return List.of(Component.translatable(key,
				CustomDescEnchantment.eff(getEffect(lv))
		).withStyle(color.desc()));
	}

}
