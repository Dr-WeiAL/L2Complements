package dev.xkmc.l2complements.events;

import dev.xkmc.l2complements.content.enchantment.core.AttributeEnchantment;
import dev.xkmc.l2complements.init.registrate.LCEnchantments;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

public class EnchantmentEventHandler {

	@SubscribeEvent
	public static void onLivingAttack(LivingAttackEvent event) {
		if (event.getSource().isBypassMagic() || event.getSource().isBypassInvul())
			return;
		if (EnchantmentHelper.getEnchantmentLevel(LCEnchantments.ENCH_ENVIRONMENT.get(), event.getEntity()) > 0) {
			if (event.getSource().getEntity() == null) event.setCanceled(true);
		}
		if (EnchantmentHelper.getEnchantmentLevel(LCEnchantments.ENCH_MAGIC.get(), event.getEntity()) > 0) {
			if (event.getSource().isMagic()) event.setCanceled(true);
		}
		if (event.getSource().isBypassEnchantments())
			return;
		if (EnchantmentHelper.getEnchantmentLevel(LCEnchantments.ENCH_PROJECTILE.get(), event.getEntity()) > 0) {
			if (event.getSource().isProjectile()) event.setCanceled(true);
		}
		if (EnchantmentHelper.getEnchantmentLevel(LCEnchantments.ENCH_FIRE.get(), event.getEntity()) > 0) {
			if (event.getSource().isFire()) event.setCanceled(true);
		}
		if (EnchantmentHelper.getEnchantmentLevel(LCEnchantments.ENCH_EXPLOSION.get(), event.getEntity()) > 0) {
			if (event.getSource().isExplosion()) event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onItemAttributes(ItemAttributeModifierEvent event) {
		ItemStack stack = event.getItemStack();
		if (stack.isEnchanted()) {
			for (Map.Entry<Enchantment, Integer> ent : EnchantmentHelper.getEnchantments(stack).entrySet()) {
				if (ent.getKey() instanceof AttributeEnchantment attr) {
					attr.addAttributes(ent.getValue(), event);
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onInventoryDrop(LivingDropsEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player))
			return;
		event.getDrops().removeIf(e -> e.getItem().getEnchantmentLevel(LCEnchantments.SOUL_BOUND.get()) > 0 && player.getInventory().add(e.getItem()));
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		event.getEntity().getInventory().replaceWith(event.getOriginal().getInventory());
	}

}
