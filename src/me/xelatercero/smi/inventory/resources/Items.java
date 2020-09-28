package me.xelatercero.smi.inventory.resources;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.xelatercero.smi.util.Enchanter;

public class Items {
	
	//Send mail Button
	public static ItemStack sendMailItemButton() {
		Enchantment enchant = new Enchanter(Text.enchanter_namespace);
		ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(enchant, 0, true);
		meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ENVIAR");
		item.setItemMeta(meta);
		return item;
		
	}
	
	//next and previous buttons
	public static ItemStack nextButton() {
		
		ItemStack item = new ItemStack(Material.GREEN_WOOL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "SIGUIENTE");
		item.setItemMeta(meta);
		return item;
		
	}
	
	//settings button : notifications
	public static ItemStack notofications(String name) {
		
		Enchantment enchant = new Enchanter(Text.enchanter_namespace);
		ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "NOTIFICACIONES: " + name);
		meta.addEnchant(enchant, 0, true);
		item.setItemMeta(meta);
		return item;
		
	}
	//settings button : notifications
	public static ItemStack sound() {
			
		Enchantment enchant = new Enchanter(Text.enchanter_namespace);
		ItemStack item = new ItemStack(Material.JUKEBOX);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "SONIDO");
		meta.addEnchant(enchant, 0, true);
		item.setItemMeta(meta);
		return item;
			
		}
	
	
	//SOUND
	
	public static ItemStack soundSent(String name) {
		
		Enchantment enchant = new Enchanter(Text.enchanter_namespace);
		ItemStack item = new ItemStack(Material.JUKEBOX);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "ENVIADO: " + name);
		meta.addEnchant(enchant, 0, true);
		item.setItemMeta(meta);
		return item;
			
		}
	
	public static ItemStack soundEmpty(String name) {
		
		Enchantment enchant = new Enchanter(Text.enchanter_namespace);
		ItemStack item = new ItemStack(Material.JUKEBOX);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "BUZON VACIO: " + name);
		meta.addEnchant(enchant, 0, true);
		item.setItemMeta(meta);
		return item;
			
		}
	
	public static ItemStack soundRecived(String name) {
	
	Enchantment enchant = new Enchanter(Text.enchanter_namespace);
	ItemStack item = new ItemStack(Material.JUKEBOX);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "RECIBIDO: " + name);
	meta.addEnchant(enchant, 0, true);
	item.setItemMeta(meta);
	return item;
		
	}
	
	public static ItemStack soundOpenMail(String name) {
	
	Enchantment enchant = new Enchanter(Text.enchanter_namespace);
	ItemStack item = new ItemStack(Material.JUKEBOX);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "ABRIR BUZON: " + name);
	meta.addEnchant(enchant, 0, true);
	item.setItemMeta(meta);
	return item;
		
	}
	
	
	
	
	
	
	//---------------------------------
	
	public static ItemStack previousButton() {
			
		ItemStack item = new ItemStack(Material.RED_WOOL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "ANTERIOR");
		item.setItemMeta(meta);
		return item;
			
		}
	public static ItemStack fillers() {
		
		ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		item.setItemMeta(meta);
		return item;
		
	}
	
	
	//Crate item
	public static ItemStack createCrate(String name, List<String> lore , int id) {
			Enchantment enchant = new Enchanter(Text.enchanter_namespace);
			ItemStack item = new ItemStack(Material.CHEST);
			ItemMeta meta = item.getItemMeta();
			meta.addEnchant(enchant, 0, true);
			meta.setCustomModelData(id);
			meta.setDisplayName(ChatColor.WHITE + "DE: " + ChatColor.AQUA + name);
			meta.setLore(lore);
			item.setItemMeta(meta);
			return item;
	
	}
	
	

}
