package me.xelatercero.smi.command;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.xelatercero.smi.inventory.SendMailInventory;
import me.xelatercero.smi.inventory.SettingsInventory;
import me.xelatercero.smi.inventory.resources.Text;
import me.xelatercero.smi.util.ConfigSettingSet;
import me.xelatercero.smi.util.InventoryManager;
import me.xelatercero.smi.util.Pagination;
import net.md_5.bungee.api.ChatColor;

public class SmiCommand implements CommandExecutor {
	
	public String command = "smi";
	public static HashMap<Player, Player> targetMap = new HashMap<Player, Player>();
	public static HashMap<Player, Pagination> playerPagination = new HashMap<Player, Pagination>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (cmd.getName().equalsIgnoreCase(command)) {
				
				if (args.length == 0) {
					player.sendMessage(Text.HELP);
					return true;
				}
				
				if(args.length == 1) {
					
					
					if(args[0].equalsIgnoreCase("open")) {
						Pagination pag = new Pagination(player, InventoryManager.mailStorage.get(player.getUniqueId()));
						playerPagination.put(player, pag);
						pag.doesPlayerHaveMail();
						return true;
					} else if (args[0].equalsIgnoreCase("settings")) {
						SettingsInventory settings = new SettingsInventory(player);
						settings.openInventory(player);
						return true;
						
					} else if (args[0].equalsIgnoreCase("fav")) {
						player.sendMessage(Text.SPECIFY_PLAYER);
						return true;
					}
					
					 else if (args[0].equalsIgnoreCase("send")) {
						player.sendMessage(Text.SPECIFY_PLAYER);
						return true;
					}
					
					 else if (args[0].equalsIgnoreCase("unfav")) {
							player.sendMessage(Text.SPECIFY_PLAYER);
							return true;
					}
					
					 else if (args[0].equalsIgnoreCase("fl")) {
						 	ConfigSettingSet config = new ConfigSettingSet(player);
						 	config.getFavList();
						 	return true;
					 }
					
					 else if (args[0].equalsIgnoreCase("help")) {
						 	Text.helpMessage(player);
						 	return true;
					 }
									
					  else {
						player.sendMessage(Text.HELP);
						return true;
					}
						
					
					
				}
				
				if(args.length == 2) {
					if (args[0].equalsIgnoreCase("send")) {
						
						Player target = Bukkit.getPlayer(args[1]);
						
						if(target != null) {
							targetCheck(player, target);
							SendMailInventory mail = new SendMailInventory();
							mail.openInventory(player);
							return true;
						} else {
							player.sendMessage(Text.PLAYER_NO_EXIST);
							return true;
						}
						
						
						
					} else if (args[0].equalsIgnoreCase("fav")) {
						
						if(args[1] == null) {
							player.sendMessage(Text.SPECIFY_PLAYER);
							return true;
						} else {
							Player target = Bukkit.getPlayer(args[1]);
							
							if(target != null) {
								ConfigSettingSet config = new ConfigSettingSet(player);
								config.addPlayerToFavorites(target);
								return true;
								
							} else {player.sendMessage(Text.PLAYER_NO_EXIST); return true;}
						}
						
						
					}
					
					 else if (args[0].equalsIgnoreCase("unfav")) {
						
						if(args[1] == null) {
							player.sendMessage(Text.SPECIFY_PLAYER);
							return true;
						} else {
							
								ConfigSettingSet config = new ConfigSettingSet(player);
								config.removePlayerFromFavorites(args[1]);
								return true;
								
							
						}
						
						
					}
					
					
					else {
						player.sendMessage(Text.HELP);
						return true;
						
					}
				}
				
			}
			
		} else {
			sender.sendMessage(ChatColor.RED + "This commands is only for players");
			return true;
		}
		
		
		return false;
	}
	
	//targetMap check
	private void targetCheck(Player player, Player target) {
		
		if(targetMap.containsKey(player)) {
			targetMap.remove(player);
			targetMap.put(player, target);
		} else {
			targetMap.put(player, target);
		}
		
	}

}
