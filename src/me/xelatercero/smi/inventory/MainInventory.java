
package me.xelatercero.smi.inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import me.xelatercero.smi.Main;


public class MainInventory implements InventoryHolder {
	
	Main plugin;
	Inventory inv;
	Player player;
	
	HashMap<Integer, ItemStack> storeList = new HashMap<Integer, ItemStack>();
	
	
	public MainInventory(String inventoryName, int slotNumber) {
		inv = Bukkit.createInventory(this, slotNumber, inventoryName);
		plugin = Main.getMainInstance();
	}
	
	
	//open inventory for player
	public void openInventory(Player player) {
		this.player = player;
		this.player.openInventory(inv);
	}
	
	//store items
	public void storeInventory(Inventory inv, int from) {
		
		ItemStack[] items = inv.getContents();
		
		for(int i = from; i<items.length;i++) {
			if(items[i] != null) {
				storeList.put(i, items[i]);
			}
			
		}
		
	}
	
	//restore items
	public void restoreInventory(Inventory inventory) {
		
		if(!(storeList.isEmpty())) {
			
			
			
			
			Iterator<Entry<Integer, ItemStack>> it = storeList.entrySet().iterator();
			
			while(it.hasNext()) {
				Entry<Integer, ItemStack> key = it.next();
				inventory.setItem(key.getKey(), key.getValue());
				it.remove();
			}
				
			
			
		}
		
	}
	
	//Set buttons
	@Nullable
	public void setButton(int[] slot, ItemStack[] item , Inventory inv) {
		
		for(int i = 0;i<slot.length;i++) {
			
			if(item[i] != null) {
				inv.setItem(slot[i], item[i]);
			}
			
		}
		
		
		
	}
	
	//Check if inventory is empty
	public boolean isInventoryEmpty(int from, Inventory inv) {
		
		ItemStack[] items = inv.getContents();
		int nullCount = 0;
		
		for(int i = from;i<items.length;i++) {
			if(items[i] != null) {
				nullCount++;
			}
		}
		
		if(nullCount>0) {
			return false;
		}
		
		return true;
	}
	
	 
	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inv;
	}

}

 