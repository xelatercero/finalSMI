package me.xelatercero.smi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.xelatercero.smi.inventory.MailStorage;
import me.xelatercero.smi.inventory.MailboxInventory;
import me.xelatercero.smi.inventory.resources.Items;
import me.xelatercero.smi.inventory.resources.Text;

public class Pagination implements InventoryHolder{
	
	private MailboxInventory mailboxInventory;
	private MailStorage storage;
	private List<Inventory> invList = new ArrayList<Inventory>();
	private Player player;
	private int currentPage = 0;
	private double maxItemsPerPage = 27D;
	private HashMap<Integer, ItemStack> listOfItems = new HashMap<Integer, ItemStack>();
	boolean isFirstOpen = true;
	
	public Pagination(Player player, MailStorage storage) {
		
		this.player = player;
		this.storage = storage;
		this.mailboxInventory = InventoryManager.mailboxInv.get(player);
		
		
		
	}
	
	public void doesPlayerHaveMail(){
		
		if(storage.getStorage().size() == 0) {
			player.closeInventory(); player.sendMessage(Text.NO_MAILS);
		} else {
			paginate();
		}
		
	} 
	
	
	//paginate
	
	public void paginate() {

		List<HashMap<String, List<ItemStack>>> crates = storage.getStorage();
		invList = new ArrayList<Inventory>();
		listOfItems = new HashMap<Integer, ItemStack>();
		int crateSize = crates.size();
		int id = 0;
		double pagesNeeded = (double) Math.ceil(crateSize/maxItemsPerPage);
		
		
		//create inventories
		
		for(int i = 0;i<pagesNeeded;i++) {
			Inventory inv = Bukkit.createInventory(this, 36, ChatColor.BLUE + "Mailbox Page: " + (i + 1));
			setButtons_Fillers(inv);
			invList.add(inv);
		}
		
	if (storage.getStorage().size() > 0) {
		
		//open mailbox sound
		if(isFirstOpen) {
			ConfigSettingSet config = new ConfigSettingSet(player);
			if(config.isSoundEnabled(4)) {
				SoundSystem sound = new SoundSystem(Ssound.OPEN_MAILBOX, player);
			}
			 
			isFirstOpen = false;
			
		}
		
		for(HashMap<String, List<ItemStack>> map : crates) {	
			
			for(Entry<String , List<ItemStack>> entry : map.entrySet()) {
				
				//here we get the sender name
				UUID senderUUID = UUID.fromString(entry.getKey());
				Player senderName = Bukkit.getPlayer(senderUUID);
				String sender = "";
				if(senderName != null) {
					sender = senderName.getName();
				}
				
				ItemStack item = Items.createCrate(sender, Text.getCrateLore(entry.getValue()), id);
				listOfItems.put(id, item);
				id++;
					
				
			}
			
			
			
			
			
		}
			
		int itemCount = 0;
		
			
			for(int i = 0; i<invList.size();i++) {
				Inventory inv = invList.get(i);				
				
				for(int b = 0;b<maxItemsPerPage;b++) { 
					
					if(listOfItems.get(itemCount) != null) {
						
						if(b==27) {
							inv.setItem(b, listOfItems.get(itemCount));
							itemCount++;
							break;
						} else {
							inv.setItem(b, listOfItems.get(itemCount));
							itemCount++;
						} 
						
						
					} else {
						break;
					}
					
					
					
					
					
				}
				
			}
			
			player.openInventory(invList.get(currentPage));
			
		
	
	} else {player.closeInventory(); player.sendMessage(Text.NO_MORE_MAILS);}
	}
	
	//set buttons and fillers
	
	private void setButtons_Fillers(Inventory inv) {
		
		inv.setItem(27, Items.previousButton());
		for(int i = 28;i<35;i++) {
			inv.setItem(i, Items.fillers());
		}
		inv.setItem(35, Items.nextButton());
		
	}
	
	//next page
	public void nextPage() {
		int nextPage = currentPage + 1;
		if(invList.size() > nextPage) {
			player.openInventory(invList.get(nextPage));
			currentPage++;
		} else {
			player.sendMessage(Text.NO_PAGES);
		}
	}
	
	//previous page
	public void previousPage() {
		int previousPage = currentPage - 1;
		if(previousPage >= 0) {
			player.openInventory(invList.get(previousPage));
			currentPage--;
			
		} else {
			player.sendMessage(Text.NO_PAGES);
		}
	}
	
	//private void 
	private void setRightPage() {
		
		double pagesNeeded = (double) Math.ceil(storage.getStorage().size()/maxItemsPerPage) - 1;
		while(currentPage > pagesNeeded) {
			currentPage--;
		}
		
		
		
	}
	
	//get items from crate 
	public void getItemsFromCrate(ItemStack crate) {
		
		if(storage.getStorage().size() > 0) {
			if(crate.hasItemMeta()) {
				ItemMeta meta = crate.getItemMeta();
				int id = meta.getCustomModelData();
				
				HashMap<String, List<ItemStack>> items = storage.getStorage().get(id);
				
				for(Entry<String, List<ItemStack>> entry : items.entrySet()) {
					
					for(ItemStack item : entry.getValue()) {
						if(item != null) {
							HashMap<Integer, ItemStack> excess = player.getInventory().addItem(item);
							
							for(Entry<Integer, ItemStack> et : excess.entrySet()) {
								
								 if(et.getValue() != null) {
									 player.getWorld().dropItem(player.getLocation(), et.getValue());
								 }
								 
								
							} 
							
						}
					}
					
					
					
				}
				storage.getStorage().remove(id);
				invList.get(currentPage).remove(crate);
				setRightPage();
				paginate();
				
			}
		}
		
		
	}
	
	
	
	private void checkSpace(List<ItemStack> list) {
		
		int nItems = list.size();
		
		Inventory player_inventory = player.getInventory();
		
		
		
	}


	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

}
