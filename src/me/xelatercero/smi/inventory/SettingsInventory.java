package me.xelatercero.smi.inventory;


import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.xelatercero.smi.Main;
import me.xelatercero.smi.inventory.resources.Items;
import me.xelatercero.smi.inventory.resources.Text;
import me.xelatercero.smi.util.ConfigSettingSet;

public class SettingsInventory extends MainInventory{
	
	Main plugin;

	public SettingsInventory(Player player ) {
		super(Text.SETTINGSNVENTORY_NAME, 9);
		this.player = player;
		plugin = Main.getMainInstance();
	}
	
	
	@Override
	public void openInventory(Player player) {

		super.openInventory(player);
		ConfigSettingSet config = new ConfigSettingSet(player);
		int[] slots = {2,4};
		ItemStack[] items = {Items.notofications(config.getNotifications()), Items.sound()};
		setButton(slots, items, inv);
	}

}
