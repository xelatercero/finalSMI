package me.xelatercero.smi.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.xelatercero.smi.Main;
import me.xelatercero.smi.inventory.resources.Items;
import me.xelatercero.smi.inventory.resources.Text;
import me.xelatercero.smi.util.ConfigSettingSet;

public class SoundInventory extends MainInventory {
	
	Main plugin;

	public SoundInventory() {
		super(Text.SOUNDINVENTORY_NAME, 9);
		plugin = Main.getMainInstance();
	}
	
	
	@Override
	public void openInventory(Player player) {
		super.openInventory(player);
		ConfigSettingSet config = new ConfigSettingSet(player);
		int[] slots = {1,3,5,7};
		ItemStack[] items = {Items.soundSent(config.getSentSound()),Items.soundEmpty(config.getEmptySound()),Items.soundRecived(config.getRecivedSound()),Items.soundOpenMail(config.getOpenSound())};
		setButton(slots, items, inv);
	}

}
