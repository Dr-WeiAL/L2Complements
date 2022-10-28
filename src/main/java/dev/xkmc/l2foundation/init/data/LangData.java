package dev.xkmc.l2foundation.init.data;

import dev.xkmc.l2foundation.init.L2Foundation;
import dev.xkmc.l2foundation.init.registrate.LFEffects;
import dev.xkmc.l2library.repack.registrate.providers.RegistrateLangProvider;
import dev.xkmc.l2library.repack.registrate.util.entry.RegistryEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

public class LangData {

	public enum IDS {
		WIND_BOTTLE("wind_bottle", "Used to obtain Captured Wind or Captured Bullet.", 0),
		VOID_EYE("void_eye", "Obtained by killing an angry Enderman %s block below the void.", 1),
		SUN_MEMBRANE("sun_membrane", "Obtained by killing a sun-burning Phantom %s blocks above max build height.", 1),
		SOUL_FLAME("soul_flame", "Obtained by killing a ghast with soul flame.", 0),
		CAPTURED_WIND("captured_wind", "Obtained by right clicking Wind Bottle when moving faster than %s blocks per second.", 1),
		CAPTURED_BULLET("captured_shulker_bullet", "Obtained by right clicking shulker bullet with Wind Bottle.", 0),
		EXPLOSION_SHARD("explosion_shard", "Obtained by surviving an explosion damage of at least %s.", 1),
		HARD_ICE("hard_ice", "Obtained by killing a Drowned with Powdered Snow.", 0),
		STORM_CORE("storm_core", "Obtained by killing a Phantom with explosion.", 0),
		BLACKSTONE_CORE("blackstone_core", "Obtained by killing a Guardian that has Stone Cage effect.", 0),
		RESONANT_FEATHER("resonant_feather", "Obtained when Warden kills a chicken with sonic boom.", 0),
		SPACE_SHARD("space_shard", "Obtained by causing a projectile damage of at least %s.", 1),
		FORCE_FIELD("force_field", "Obtained by killing a wither with arrow.", 0);

		final String id, def;
		final int count;

		IDS(String id, String def, int count) {
			this.id = id;
			this.def = def;
			this.count = count;
		}

		public MutableComponent get(Object... objs) {
			if (objs.length != count)
				throw new IllegalArgumentException("for " + name() + ": expect " + count + " parameters, got " + objs.length);
			return translate(L2Foundation.MODID + "." + id, objs);
		}

	}

	public interface LangEnum<T extends Enum<T> & LangEnum<T>> {

		int getCount();

		@Nullable
		default Class<? extends Enum<?>> mux() {
			return null;
		}

		@SuppressWarnings({"unchecked"})
		default T getThis() {
			return (T) this;
		}

	}

	public static void addTranslations(RegistrateLangProvider pvd) {
		for (IDS id : IDS.values()) {
			String[] strs = id.id.split("\\.");
			String str = strs[strs.length - 1];
			pvd.add(L2Foundation.MODID + "." + id.id,
					RegistrateLangProvider.toEnglishName(str) + getParams(id.count));
		}

		pvd.add("itemGroup.l2foundation.generated", "L2Fundation Items");
		pvd.add("death.attack.soul_flame", "%s has its soul burnt out");
		pvd.add("death.attack.soul_flame.player", "%s has its soul burnt out by %s");
		List<Item> list = List.of(Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION, Items.TIPPED_ARROW);
		for (RegistryEntry<? extends Potion> ent : LFEffects.POTION_LIST) {
			for (Item item : list) {
				String str = ent.get().getName(item.getDescriptionId() + ".effect.");
				pvd.add(str, RegistrateLangProvider.toEnglishName(ent.get().getName("")));
			}
		}
	}

	private static String getParams(int count) {
		if (count <= 0)
			return "";
		StringBuilder pad = new StringBuilder();
		for (int i = 0; i < count; i++) {
			pad.append(pad.length() == 0 ? ": " : "/");
			pad.append("%s");
		}
		return pad.toString();
	}

	public static String asId(String name) {
		return name.toLowerCase(Locale.ROOT);
	}

	public static MutableComponent translate(String key, Object... objs) {
		return Component.translatable(key, objs);
	}

}
