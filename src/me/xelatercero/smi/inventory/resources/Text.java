package me.xelatercero.smi.inventory.resources;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.xelatercero.smi.Main;

public class Text {
	
	//prefix
	public static final String prefix = ChatColor.RED + "[" + ChatColor.GOLD + "SMI" + ChatColor.RED + "] ";
	
	//mailbox name
	public static final String MAILINVENTORY_NAME = ChatColor.RED + "Buzon";
	//send mail inventory name
	public static final String SENDMAILINVENTORY_NAME = ChatColor.RED + "Enviar correo";
	//setting inventory name
	public static final String SETTINGSNVENTORY_NAME = ChatColor.DARK_RED + "Configuracion";
	//sound inventory name
	public static final String SOUNDINVENTORY_NAME = ChatColor.DARK_RED + "Sonido";
	
	//sound buttons name and lore
	
	public static final String SOUND_SENT_ENABLED = ChatColor.GREEN + "SENT: " + ChatColor.AQUA + "YES";
	public static final String SOUND_SENT_DISABLED = ChatColor.GREEN + "SENT: " + ChatColor.RED + "NO";
	
	
	
	//Help
	public static void helpMessage(Player player) {
		
		player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------SMI-----------------");
		player.sendMessage(ChatColor.RED + "/smi open " + ChatColor.GOLD + "-->" + ChatColor.AQUA + " Abre el buzon");
		player.sendMessage(ChatColor.RED + "/smi settings" + ChatColor.GOLD + "-->" +  ChatColor.AQUA + " Abre el menu de configuracion");
		player.sendMessage(ChatColor.RED + "/smi fav [player]" + ChatColor.GOLD + "-->" + ChatColor.AQUA + " Agrega jugador a la lista de favoritos");
		player.sendMessage(ChatColor.RED + "/smi unfav [player]" + ChatColor.GOLD + "-->" + ChatColor.AQUA + " Quita jugador de la lista de favoritos");
		player.sendMessage(ChatColor.RED + "/smi fl" + ChatColor.GOLD + "-->" + ChatColor.AQUA + " Muestra la lista de jugadores favoritos");
		player.sendMessage(ChatColor.RED + "/smi send [player]" + ChatColor.GOLD + "-->" + ChatColor.AQUA + " Abre la interfaz para enviar correos");
		player.sendMessage(ChatColor.RED + "/smi help" + ChatColor.AQUA + ChatColor.GOLD + "-->" + ChatColor.AQUA + " Muestra este texto");	
		player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "-------------------------------------");
		
		
		
	}
	
	//need help?
	public static final String HELP = prefix +  ChatColor.RED + "Comando incorrecto intenta usar: " + ChatColor.AQUA + "/smi help";
	//specify a player
	public static final String SPECIFY_PLAYER = prefix + ChatColor.RED + "Por favor especifica un jugador ";
	//player doesn't exist/offline
	public static final String PLAYER_NO_EXIST = prefix + ChatColor.RED + "El jugador no existe o esta offline";
	//send mail empty
	public static final String SEND_MAIL_EMPTY = prefix + ChatColor.RED + "No hay nada para enviar!";
	//mail sended
	public static final String MAIL_SENDED = prefix + ChatColor.GREEN + "Correo enviado!";
	//no mails
	public static final String NO_MAILS = prefix + ChatColor.RED + "No tienes ningun correo :(";
	public static final String NO_MORE_MAILS = prefix + ChatColor.RED + "Ese era el ultimo correo";
	//no more pages
	public static final String NO_PAGES = prefix + ChatColor.RED + "No hay mas paginas";
	
	
	
	
	
	//Namespaced key for the "Enchanter"
	public static final NamespacedKey enchanter_namespace = new NamespacedKey(Main.getMainInstance(), "smi");
	
	
	//Set crate items to lore
	public static List<String> getCrateLore(List<ItemStack> itemList) {
		
		List<String> list = new ArrayList<String>();
		for(ItemStack i : itemList) {
			
			if(i.hasItemMeta()) {
				list.add(i.getItemMeta().getDisplayName().toString() + ChatColor.LIGHT_PURPLE + " x " + i.getAmount() + ChatColor.GOLD + " [" + ChatColor.GREEN + i.getType() + ChatColor.GOLD + "]");
			} else {
				list.add(i.getType().toString() +  ChatColor.LIGHT_PURPLE + " x " + i.getAmount());
			}
		
		}
	
		List<String> finalList = new ArrayList<String>();
		
		for(String s : list) {
			
			if(s.contains("_")) {
				s = ChatColor.GREEN + s.replace("_", " ");
				finalList.add(s);
			} else {
				s = ChatColor.GREEN + s;
				finalList.add(s);
			}
			
		}
		
		return finalList;
	}

}
