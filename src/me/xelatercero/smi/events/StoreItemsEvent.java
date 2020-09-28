package me.xelatercero.smi.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import me.xelatercero.smi.inventory.MailboxInventory;
import me.xelatercero.smi.inventory.SendMailInventory;
import me.xelatercero.smi.util.InventoryManager;

public class StoreItemsEvent implements Listener{
	
	InventoryManager invManager = new InventoryManager();
	
	
	@EventHandler
	public void onCloseMailbox(InventoryCloseEvent e) {
	
		if(!(e.getInventory().getHolder() instanceof MailboxInventory)) {return;}
		
		Player player = (Player) e.getPlayer();
		MailboxInventory manager_inv = InventoryManager.mailboxInv.get(player);
		
		
		manager_inv.storeInventory(e.getInventory(), 3);
		
	}
	
	
	@EventHandler
	public void onCloseSendMailInventory(InventoryCloseEvent e) {
	
		if(!(e.getInventory().getHolder() instanceof SendMailInventory)) {return;}
		
		Player player = (Player) e.getPlayer();
		SendMailInventory manager_inv = InventoryManager.sendMailInv.get(player);
		
		
		manager_inv.storeInventory(e.getInventory(), 1);
		
	}
	
}
