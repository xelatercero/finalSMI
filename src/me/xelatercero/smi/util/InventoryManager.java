package me.xelatercero.smi.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.xelatercero.smi.Main;
import me.xelatercero.smi.inventory.MailStorage;
import me.xelatercero.smi.inventory.MailboxInventory;
import me.xelatercero.smi.inventory.SendMailInventory;


public class InventoryManager {
	
	
	//HashMaps
	public static HashMap<Player, MailboxInventory> mailboxInv = new HashMap<Player, MailboxInventory>();
	public static HashMap<Player, SendMailInventory> sendMailInv = new HashMap<Player, SendMailInventory>();
	public static HashMap<UUID, MailStorage> mailStorage = new HashMap<UUID, MailStorage>();
	private static Main plugin;
	
	public InventoryManager() {
		plugin = Main.getMainInstance();
	}
	
	//
	public static  void addPlayer(Player player) {
		
		if(!(mailboxInv.containsKey(player))) {
			mailboxInv.put(player, new MailboxInventory());
		}
		if(!(sendMailInv.containsKey(player))) {
			sendMailInv.put(player, new SendMailInventory());
			
		}
		
		
		if(!mailStorage.containsKey(player.getUniqueId())) {
		
			YamlConfiguration config = plugin.getMailConfig();
			if(config.contains("Storage." + player.getUniqueId())) {
				List<HashMap<String, List<ItemStack>>> list = (List<HashMap<String, List<ItemStack>>>) config.get("Storage." + player.getUniqueId());
				MailStorage storage  = new MailStorage();
				storage.setStorage(list);
				
				mailStorage.put(player.getUniqueId(), storage);
			} else {
				mailStorage.put(player.getUniqueId(), new MailStorage());
			}
		
			
			
			
		 }
		
	}
	
	
	
	//creates an instance of all inventories for every player on reload
	public static void assingInventoriesOnReload() {
		
		 Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
		 
		 for(Player p : players) {
			 
			 if(!(mailboxInv.containsKey(p))) {
				 mailboxInv.put(p, new MailboxInventory());
			 }
			 if(!(sendMailInv.containsKey(p))) {
				 sendMailInv.put(p, new SendMailInventory());
			}
			 
			 
		 }
		
	}
	
	 
	//
	public void serializeStorage() {
		
		for(Entry<UUID, MailStorage> entry : mailStorage.entrySet()) {
			if(entry != null) {
				UUID playerUUID = entry.getKey();
				MailStorage storage = entry.getValue();
				YamlConfiguration config = plugin.getMailConfig();
				int nulls = 0;
				
				for(HashMap<String, List<ItemStack>> map : storage.getStorage()) {
					
					for(Entry<String, List<ItemStack>> en : map.entrySet()) {
						if(en.getValue() == null) {
							nulls++;
						} 
					}
					
				}
				
				if(nulls == 0) {
					config.set("Storage." + playerUUID, storage.getStorage());
					plugin.saveMailConfig();
				} 
				
			}
			
			
		}
		
		
	}
	
	//
	public void restoreStorage() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				//Setup settings on reload 
				ConfigSettingSet configSet = new ConfigSettingSet();
				configSet.reloadSetUp();
				
				 Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
				 
				 for(Player p : players) {
					 
					 if(!(mailStorage.containsKey(p.getUniqueId()))) {
						 
						 	YamlConfiguration config = plugin.getMailConfig();
							if(config.contains("Storage." + p.getUniqueId())) {
								List<HashMap<String, List<ItemStack>>> list = (List<HashMap<String, List<ItemStack>>>) config.get("Storage." + p.getUniqueId());
								MailStorage storage  = new MailStorage();
								storage.setStorage(list);
							
								mailStorage.put(p.getUniqueId(), storage);
							} else {
								mailStorage.put(p.getUniqueId(), new MailStorage());
							} 
							
						 
					 }
					 
				 }
				
				
			}
		}, 1);
	}

	
	
	
	
	

}
