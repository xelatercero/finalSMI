package me.xelatercero.smi.util;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.xelatercero.smi.Main;
import me.xelatercero.smi.inventory.resources.Text;
import net.md_5.bungee.api.ChatColor;

public class ConfigSettingSet {
	
	Main plugin;
	Player player;
	
	public ConfigSettingSet(Player player) {
		plugin = Main.getMainInstance();
		this.player = player;
	}
	
	public ConfigSettingSet() {
		plugin = Main.getMainInstance();
		
	}
	
	//first setup
	
		public void firstSetup(Player player) {
			
			FileConfiguration config = plugin.getConfig();
			
			if(!(config.contains("Settings." + player.getUniqueId()))) {
				List<String> filter = new ArrayList<String>();
				config.set("Settings." + player.getUniqueId() + ".name",player.getName());
				config.set("Settings." + player.getUniqueId() + ".notifications",true);
				config.set("Settings." + player.getUniqueId() + ".notifications_filter",filter);
				config.set("Settings." + player.getUniqueId() + ".sound.sent",true);
				config.set("Settings." + player.getUniqueId() + ".sound.empty",true);
				config.set("Settings." + player.getUniqueId() + ".sound.recived",true);
				config.set("Settings." + player.getUniqueId() + ".sound.open_mail",true);
				plugin.saveConfig();
			}
			
		}
		
	//set up on reload 
		
		public void reloadSetUp() {
			
			Collection<? extends Player> players = Bukkit.getOnlinePlayers();
			
			for(Player p : players) {
				firstSetup(p);
			}
			
		}
		
		public void setNotifications() {
			
			FileConfiguration config = plugin.getConfig();
			boolean not = config.getBoolean("Settings." + player.getUniqueId() + ".notifications");
			
			if(not) {
				config.set("Settings." + player.getUniqueId() + ".notifications", false);
			} else {
				config.set("Settings." + player.getUniqueId() + ".notifications", true);
			}
			
			plugin.saveConfig();
		}
		
		public String getNotifications() {
			
			FileConfiguration config = plugin.getConfig();
			boolean isEnabled = config.getBoolean("Settings." + player.getUniqueId() + ".notifications");
			
			if(isEnabled) {
				return ChatColor.AQUA + "SI";
			} else {
				return ChatColor.RED + "NO";
			}
			
		}
		
		//SOUND SETTINGS-------------------------------------------------
		public String getSentSound() {
			
			FileConfiguration config = plugin.getConfig();
			boolean isEnabled = config.getBoolean("Settings." + player.getUniqueId() + ".sound.sent");;
			
			if(isEnabled) {
				return ChatColor.AQUA + "SI";
			} else {
				return ChatColor.RED + "NO";
			}
			
		}
		
		public String getOpenSound() {
			
			FileConfiguration config = plugin.getConfig();
			boolean isEnabled = config.getBoolean("Settings." + player.getUniqueId() + ".sound.open_mail");;
			
			if(isEnabled) {
				return ChatColor.AQUA + "SI";
			} else {
				return ChatColor.RED + "NO";
			}
			
		}
		
		public String getEmptySound() {
			
			FileConfiguration config = plugin.getConfig();
			boolean isEnabled = config.getBoolean("Settings." + player.getUniqueId() + ".sound.empty");;
			
			if(isEnabled) {
				return ChatColor.AQUA + "SI";
			} else {
				return ChatColor.RED + "NO";
			}
			
		}
		
		public String getRecivedSound() {
			
			FileConfiguration config = plugin.getConfig();
			boolean isEnabled = config.getBoolean("Settings." + player.getUniqueId() + ".sound.recived");;
			
			if(isEnabled) {
				return ChatColor.AQUA + "SI";
			} else {
				return ChatColor.RED + "NO";
			}
			
		}
		
		
		public void setSound(int row) {
			FileConfiguration config = plugin.getConfig();
			String yml;
			
			switch (row) {
			case 1:
				yml = "sent";
				break;
				
			case 3:
				yml = "empty";
				break;
			
			case 5:
				yml = "recived";
				break;
			
			case 7:
				yml = "open_mail";
				break;

			default:
				return;
			}
			
			boolean not = config.getBoolean("Settings." + player.getUniqueId() + ".sound." + yml);
			
			if(not) {
				config.set("Settings." + player.getUniqueId() + ".sound." + yml, false);
			} else {
				config.set("Settings." + player.getUniqueId() + ".sound." + yml, true);
			}
			plugin.saveConfig();
			
			
		}
		
		public boolean isSoundEnabled(int option) {
			FileConfiguration config = plugin.getConfig();
			
			switch(option) {
				
			case 1:
				return config.getBoolean("Settings." + player.getUniqueId() + ".sound.sent");
			case 2:
				return config.getBoolean("Settings." + player.getUniqueId() + ".sound.empty");
			case 3:
				return config.getBoolean("Settings." + player.getUniqueId() + ".sound.recived");
			case 4:
				return config.getBoolean("Settings." + player.getUniqueId() + ".sound.open_mail");
			default:
				return false;
			
			}
				
			
		}
		//---------------------------------------------------------------------------
		
		public boolean isNotificationsEnabled() {
			return plugin.getConfig().getBoolean("Settings." + player.getUniqueId() + ".notifications");
		}
		
		public void addPlayerToFavorites(Player target) {
			FileConfiguration config = plugin.getConfig();
			List<String> names = (List<String>) config.getList("Settings." + player.getUniqueId() + ".notifications_filter");		
			if(names.contains(target.getName())) {
				player.sendMessage(Text.prefix + ChatColor.RED + "El jugador ya esta en favoritos");
			} else {
				names.add(target.getName());
				config.set("Settings." + player.getUniqueId() + ".notifications_filter",names);
				plugin.saveConfig();
				player.sendMessage(Text.prefix + ChatColor.AQUA + target.getName() + ChatColor.GREEN + " agregado a favoritos");
			}
			
		}
		
		public void removePlayerFromFavorites(String  target) { 
			FileConfiguration config = plugin.getConfig();
			List<String> names = (List<String>) config.getList("Settings." + player.getUniqueId() + ".notifications_filter");		
			int indexToDelete = 0;
			if(names.contains(target)) {
				
				for(int i = 0;i<names.size();i++) {
					
					if(names.get(i).equals(target)) {
						indexToDelete = i;
						break;
					}
				}
				
				names.remove(indexToDelete);
				config.set("Settings." + player.getUniqueId() + ".notifications_filter",names);
				plugin.saveConfig();
				player.sendMessage(Text.prefix + ChatColor.AQUA + target + ChatColor.GREEN + " eliminado de la lista");
				
			} else {
				player.sendMessage(Text.prefix + ChatColor.AQUA + target + ChatColor.RED + " no esta en favoritos");
			}
		}
		
		public boolean isPlayerOnFav(Player target) {
			FileConfiguration config = plugin.getConfig();
			List<String> names = (List<String>) config.getList("Settings." + player.getUniqueId() + ".notifications_filter");
			return names.contains(target.getName());
		}
		
		public void getFavList() {
			FileConfiguration config = plugin.getConfig();
			List<String> names = (List<String>) config.getList("Settings." + player.getUniqueId() + ".notifications_filter");
			
			if(names.isEmpty()) {
				player.sendMessage(Text.prefix + ChatColor.RED + "No tienes ningun jugador agregado");
			} else {
				List<String> msg = new ArrayList<String>();
				msg.add(ChatColor.GOLD + "" + ChatColor.BOLD + "------------------");
				for (String n : names) {
					msg.add(" - " + ChatColor.AQUA + n);
				}
				msg.add(ChatColor.GOLD + "" + ChatColor.BOLD + "------------------");
				
				for(String s : msg) {
					player.sendMessage(s);
				}
			}
		}

}
