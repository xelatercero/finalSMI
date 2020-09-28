package me.xelatercero.smi.inventory;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.xelatercero.smi.inventory.resources.Items;
import me.xelatercero.smi.inventory.resources.Text;
import me.xelatercero.smi.util.ConfigSettingSet;
import me.xelatercero.smi.util.InventoryManager;
import me.xelatercero.smi.util.SoundSystem;
import me.xelatercero.smi.util.Ssound;
import net.md_5.bungee.api.ChatColor;

public class SendMailInventory extends MainInventory{

	public SendMailInventory() {
		super(Text.SENDMAILINVENTORY_NAME, 9);
		
	}
	
	@Override
	public void openInventory(Player player) {
			super.openInventory(player);
			int[] slot = {0};
			ItemStack[] item = {Items.sendMailItemButton()};
			setButton(slot, item, this.inv);
	}
	
	//send items method 
	public void sendItems(Player player, Player target , Inventory inv) {
		ConfigSettingSet soundConfig = new ConfigSettingSet(player);
		
		if (isInventoryEmpty(1, inv)) {
			player.sendMessage(Text.SEND_MAIL_EMPTY);
			
			if(soundConfig.isSoundEnabled(2)) {
				SoundSystem sound = new SoundSystem(Ssound.EMPTY, player);
			}
			
		} else {
			
			ItemStack[] items = inv.getContents();
			List<ItemStack> itemsToSend = new ArrayList<ItemStack>();
			
			for(int i = 1;i<items.length;i++) {
				
				if(items[i] != null) {
					itemsToSend.add(items[i]);
					inv.remove(items[i]);
				}
				
			}
			
			MailStorage storage = InventoryManager.mailStorage.get(target.getUniqueId());
			storage.addToMailStorage(player, itemsToSend,false);
			
			//send notificatation to target if enabled
			ConfigSettingSet config = new ConfigSettingSet(target);
			if(config.isNotificationsEnabled() || config.isPlayerOnFav(player)) {
				target.sendMessage(Text.prefix + ChatColor.AQUA + player.getName() + ChatColor.GREEN + " Te ha enviado correo!");
				
				if(soundConfig.isSoundEnabled(3)) {
				SoundSystem sound = new SoundSystem(Ssound.RECIVED, player);
				}
				
			}
			
			if(soundConfig.isSoundEnabled(1)) {
			SoundSystem sound = new SoundSystem(Ssound.SENT, player);
			}
		}
		
	}
	
	//method for ezAuctions
	public void sendItemsFromAuction(Player player, @Nonnull ItemStack item) {
		
		ConfigSettingSet soundConfig = new ConfigSettingSet(player);
		List<ItemStack> itemsToSend = new ArrayList<ItemStack>();
		itemsToSend.add(item);
		MailStorage storage = InventoryManager.mailStorage.get(player.getUniqueId());
		storage.addToMailStorage(player, itemsToSend, true);
		
		
		ConfigSettingSet config = new ConfigSettingSet(player);
		if(config.isNotificationsEnabled()) {
			player.sendMessage(Text.prefix + ChatColor.GREEN + "Has recibido el objeto!");
		}
		
		if(soundConfig.isSoundEnabled(3)) {
			SoundSystem sound = new SoundSystem(Ssound.RECIVED, player);
			}
		
	}

}
