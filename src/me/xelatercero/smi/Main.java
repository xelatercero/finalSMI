package me.xelatercero.smi;

import java.io.File;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import me.xelatercero.smi.command.SmiCommand;
import me.xelatercero.smi.events.InventoryClickEvents;
import me.xelatercero.smi.events.PlayerJoinEvents;
import me.xelatercero.smi.events.RestoreItemsEvent;
import me.xelatercero.smi.events.StoreItemsEvent;
import me.xelatercero.smi.inventory.resources.Text;
import me.xelatercero.smi.util.Enchanter;
import me.xelatercero.smi.util.InventoryManager;

public class Main extends JavaPlugin {
	
	SmiCommand smiCommand = new SmiCommand();
	private static Main plugin;
	File storeFiel;
	FileConfiguration storeConfig;
	
	@Override
	public void onEnable() {
		
		//init plugin instance
		plugin = this;
		
		//main command 
		getCommand(smiCommand.command).setExecutor(new SmiCommand());
		//events
		registerEvents();
		
		//register ecnhantments
		registerEnchantment();
		
		//passing inventories instances on reload
		InventoryManager.assingInventoriesOnReload();
		
		//create folder
		createFolder();
		
		//create mail config
		createConfig();
		
		//create settings config
		registerConfig();
		
		
		//InventoryManager restore storage on reload
		InventoryManager manager = new InventoryManager();
		manager.restoreStorage();
		
		
		 
	}
	
	
	@Override
	public void onDisable() {
		
		InventoryManager manager = new InventoryManager();
		manager.serializeStorage();
		
		plugin = null;
		
		
	}
	
	
	//get main instance 
	public static Main getMainInstance() {
		return plugin;
	}
	
	//register events
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new StoreItemsEvent(), this);
		Bukkit.getPluginManager().registerEvents(new RestoreItemsEvent(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinEvents(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClickEvents(), this);
		
	}
	
	//register custom echantment
	private void registerEnchantment() {
		
		Enchantment ench = new Enchanter(Text.enchanter_namespace);
		
		try {
			
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(ench);
			
			
		} catch (Exception e) {
			
		}
	}
	
	//config
	private void createConfig() {
		storeFiel = new File("plugins/SendMeItems/mails.yml");
		
		if(!(storeFiel.exists())) {
			try {
				
				storeFiel.createNewFile();
				
			} catch (Exception e) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "mails.yml " + ChatColor.RED +"couldn't be created , try restarting the server");
				
			}
		}
		storeConfig = YamlConfiguration.loadConfiguration(storeFiel);
		storeConfig.options().copyDefaults(true);
		saveMailConfig();
		
	}
	
	public void saveMailConfig() {
		
		try {
			
			storeConfig.save(storeFiel);
			
		} catch (Exception e) {
			
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "mails.yml " + ChatColor.RED +"couldn't be saved , try again");
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			try {
				storeConfig.save(storeFiel);
			} catch (Exception e1) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "mails.yml " + ChatColor.RED +"couldn't be saved , for second time!!");	
				System.out.println(e.getCause());
			}
			
		}
		
	}
	
	public YamlConfiguration getMailConfig() {
		return (YamlConfiguration) storeConfig;
	}
	
	//create the plugin folder
	
	public void createFolder() {
		File dir = new File("plugins/SendMeItems");
		if(!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	private void registerConfig() {
		FileConfiguration config = this.getConfig();
		config.options().copyDefaults(true);
		config.options().header("This file stores , all the settings for the players");
		config.options().copyHeader(true);
		saveConfig();
		
	}
	
	
	

}
