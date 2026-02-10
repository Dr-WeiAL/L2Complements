package dev.xkmc.l2complements.content.item.anvil;

import dev.xkmc.l2complements.init.registrate.LCBlocks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class EternalAnvilMenu extends AnvilMenu {

	public static EternalAnvilMenu createFloating(int id, Inventory inv, ContainerLevelAccess access) {
		var ans = new EternalAnvilMenu(id, inv, access);
		ans.floating = true;
		return ans;
	}

	private boolean floating = false;

	public EternalAnvilMenu(MenuType<AnvilMenu> type, int id, Inventory inv) {
		super(id, inv);
	}

	public EternalAnvilMenu(int id, Inventory inv, ContainerLevelAccess access) {
		super(id, inv, access);
	}

	@Override
	public MenuType<?> getType() {
		return LCBlocks.ETERNAL_ANVIL_MENU.get();
	}

	protected void onTake(Player pl, ItemStack stack) {
		this.inputSlots.setItem(0, ItemStack.EMPTY);
		if (this.repairItemCountCost > 0) {
			ItemStack mat = this.inputSlots.getItem(1);
			if (!mat.isEmpty() && mat.getCount() > this.repairItemCountCost) {
				mat.shrink(this.repairItemCountCost);
				this.inputSlots.setItem(1, mat);
			} else {
				this.inputSlots.setItem(1, ItemStack.EMPTY);
			}
		} else {
			this.inputSlots.setItem(1, ItemStack.EMPTY);
		}
		setMaximumCost(0);
		this.access.execute((level, pos) -> level.levelEvent(1030, pos, 0));
	}

	@Override
	public boolean stillValid(Player player) {
		return floating || super.stillValid(player);
	}

	@Override
	public void createResult() {
		super.createResult();
		var stack = this.resultSlots.getItem(0);
		if (stack.getBaseRepairCost() > 0) {
			stack.setRepairCost(0);
			this.resultSlots.setItem(0, stack);
			this.broadcastChanges();
		}
	}

}
