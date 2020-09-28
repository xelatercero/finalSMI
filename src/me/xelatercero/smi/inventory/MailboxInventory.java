package me.xelatercero.smi.inventory;



import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;


import me.xelatercero.smi.inventory.resources.Text;

public class MailboxInventory extends MainInventory implements InventoryHolder  {

	
	
	
	public MailboxInventory() {
		super(Text.MAILINVENTORY_NAME,36);
		
		
	}
	
	
	@Override
	public void openInventory(Player player) {
		super.openInventory(player);
		
		//int[] slot = {0,1,2};
		//ItemStack[] item = {Items.sendMailItemButton(),new ItemStack(Material.IRON_BLOCK),new ItemStack(Material.DIAMOND)};
		//setButton(slot, item, this.inv);
	}
	
		
	//get inventory method
	@Override
	public Inventory getInventory() {
	
		return inv;
	}

}
