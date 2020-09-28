package me.xelatercero.smi.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import me.xelatercero.smi.command.SmiCommand;
import me.xelatercero.smi.inventory.MailboxInventory;
import me.xelatercero.smi.inventory.SendMailInventory;
import me.xelatercero.smi.util.InventoryManager;

public class RestoreItemsEvent implements Listener {
	
	@EventHandler
	public void onOpenMailbox(InventoryOpenEvent e) {
		
		if(!(e.getInventory().getHolder() instanceof MailboxInventory)) {return;}
		Player player = (Player) e.getPlayer();
		SmiCommand.playerPagination.get(player).paginate();
		
	}
	
	@EventHandler
	public void onOpenSendMailInventory(InventoryOpenEvent e) {
		
		if(!(e.getInventory().getHolder() instanceof SendMailInventory)) {return;}
		Player player = (Player) e.getPlayer();
		SendMailInventory manager_inv = InventoryManager.sendMailInv.get(player);
		manager_inv.restoreInventory(e.getInventory());
	}
	
	
}
