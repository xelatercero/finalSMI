package me.xelatercero.smi.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.xelatercero.smi.inventory.resources.Text;

public class MailStorage {
	
	
	private List<HashMap<String, List<ItemStack>>> storage = new ArrayList<HashMap<String,List<ItemStack>>>();
	
	public MailStorage() {
		
	}
	
	
	//add items to storage
	public void addToMailStorage(Player sender, List<ItemStack> itemList, boolean auction) {
		
		List<ItemStack> checkList = new ArrayList<ItemStack>();
		
		for(ItemStack item : itemList) {
			
			if(item.hasItemMeta()) {
				checkList.add(item);
				continue;
			} 
			checkList.add(item);
				
			
		}
		
		HashMap<String, List<ItemStack>> listToAdd = new HashMap<String, List<ItemStack>>();
		listToAdd.put(sender.getUniqueId().toString(), checkList);
		storage.add(listToAdd);
		
		if(!auction) {
			sender.sendMessage(Text.MAIL_SENDED);
		}
		
	}
	
	//get storage
	
	public List<HashMap<String, List<ItemStack>>> getStorage() {
		
		return storage;
	}
	
	//set storage
	
	public void setStorage(List<HashMap<String, List<ItemStack>>> list) {
		
		if(list != null) {
			this.storage = list;
		} else {
			this.storage = new ArrayList<HashMap<String,List<ItemStack>>>();
		}
		
		
	}
}
