package org.zencode.shortninja.staffplus.gadgets;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.util.hex.Items;

public class StaffInventory
{
	private static Map<String, ItemStack[]> savedContents = new HashMap<String, ItemStack[]>();
	private static Map<String, ItemStack[]> savedArmor = new HashMap<String, ItemStack[]>();
	private static Player player;
	
	public StaffInventory(Player player, boolean isEnabling)
	{
		StaffInventory.player = player;
		
		if(isEnabling)
		{
			saveContents();
			setInventory();
		}else restoreContents(player);
	}
	
	public static void toggleVanish(Player player)
	{
		boolean vanished = StaffPlus.get().vanish.isActive(player.getName());
		
		if(vanished)
		{
			StaffPlus.get().vanish.disable(player);
		}else StaffPlus.get().vanish.enable(player);
			
		for(ItemStack item : player.getInventory())
		{
			if(item != null && item.hasItemMeta())
			{
				ItemMeta itemMeta = item.getItemMeta();
				
				if(itemMeta.hasDisplayName())
				{
					if(itemMeta.getDisplayName().equals(StaffPlus.get().message.colorize("&cToggle Vanish")))
					{
						player.getInventory().remove(item);
						
						ItemStack clay = Items.builder()
						        .setMaterial(Material.INK_SACK).setAmount(1).setData(getClayColorVanish(vanished))
						        .setName(StaffPlus.get().message.colorize("&cToggle Vanish"))
						        .addLore("&7Toggles your invisibility.")
						        .build();
						
						player.getInventory().setItem(StaffPlus.get().storage.vanishSlot, clay);
					}
				}
			}
		}
	}
	
	private void restoreContents(Player player)
	{
		StaffPlus.get().inventory.clear(player);
		
		player.getInventory().setContents(savedContents.get(player.getName()));
		player.getInventory().setArmorContents(savedArmor.get(player.getName()));
		
		savedContents.remove(player.getName());
		savedArmor.remove(player.getName());
	}
	
	private void saveContents()
	{
		savedContents.put(player.getName(), player.getInventory().getContents());
		savedArmor.put(player.getName(), player.getInventory().getArmorContents());
		
		StaffPlus.get().inventory.clear(player);
	}
	
	private void setInventory()
	{
		if(StaffPlus.get().storage.compassEnabled)
		{
			ItemStack compass = Items.builder()
					.setMaterial(Material.COMPASS).setAmount(1)
					.setName(StaffPlus.get().message.colorize("&4Compass Launch"))
					.addLore("&7Launches you towards the location", "&7that you are facing.")
					.build();
			
			player.getInventory().setItem(StaffPlus.get().storage.compassSlot, compass);
		}
		
		if(StaffPlus.get().storage.teleportEnabled)
		{
			ItemStack eyeOfEnder = Items.builder()
					.setMaterial(Material.EYE_OF_ENDER).setAmount(1)
					.setName(StaffPlus.get().message.colorize("&3Random Teleport"))
					.addLore("&7Teleports you to a random player.")
					.build();
			
			player.getInventory().setItem(StaffPlus.get().storage.teleportSlot, eyeOfEnder);
		}

		if(StaffPlus.get().storage.vanishEnabled)
		{
			ItemStack clay = Items.builder()
					.setMaterial(Material.INK_SACK).setAmount(1).setData(getClayColor())
					.setName(StaffPlus.get().message.colorize("&cToggle Vanish"))
					.addLore("&7Toggles your invisibility.")
					.build();
			
			player.getInventory().setItem(StaffPlus.get().storage.vanishSlot, clay);
		}
		
		if(StaffPlus.get().storage.reportsPaperEnabled)
		{
			ItemStack paper = Items.builder()
					.setMaterial(Material.PAPER).setAmount(1)
					.setName(StaffPlus.get().message.colorize("&6Reports GUI"))
					.addLore("&7Shows all unresolved reports", "&7in a GUI.")
					.build();
			
			player.getInventory().setItem(StaffPlus.get().storage.reportsPaperSlot, paper);
		}
		
		if(StaffPlus.get().storage.freezeEnabled)
		{
			ItemStack blazeRod = Items.builder()
					.setMaterial(Material.BLAZE_ROD).setAmount(1)
					.setName(StaffPlus.get().message.colorize("&bFreeze Player"))
					.addLore("&7Freezes the player that was clicked.")
					.build();
			
			player.getInventory().setItem(StaffPlus.get().storage.freezeSlot, blazeRod);
		}
		
		if(StaffPlus.get().storage.cpsEnabled)
		{
			ItemStack clock = Items.builder()
					.setMaterial(Material.WATCH).setAmount(1)
					.setName(StaffPlus.get().message.colorize("&dCPS Checker"))
					.addLore("&7Runs a clicks per second test on the", "&7player that was clicked.")
					.build();
			
			player.getInventory().setItem(StaffPlus.get().storage.cpsSlot, clock);
		}
		
		if(StaffPlus.get().storage.inventoryEnabled)
		{
			ItemStack chest = Items.builder()
					.setMaterial(Material.CHEST).setAmount(1)
					.setName(StaffPlus.get().message.colorize("&aInventory Inspect"))
					.addLore("&7Inspects the inventory of the player", "&7that was clicked.")
					.build();
			
			player.getInventory().setItem(StaffPlus.get().storage.inventorySlot, chest);
		}
		
		if(StaffPlus.get().storage.followEnabled)
		{
			ItemStack lead = Items.builder()
					.setMaterial(Material.LEASH).setAmount(1)
					.setName(StaffPlus.get().message.colorize("&eFollow Player"))
					.addLore("&7Mounts the player that was clicked.")
					.build();
			
			player.getInventory().setItem(StaffPlus.get().storage.followSlot, lead);
		}
		
		if(StaffPlus.get().inventory.isActualBlock(StaffPlus.get().storage.customItem) && StaffPlus.get().storage.customEnabled)
		{
			String[] loreArray = new String[StaffPlus.get().storage.customLoreList.size()];
			
			ItemStack custom = Items.builder()
					.setMaterial(Material.valueOf(StaffPlus.get().storage.customItem)).setAmount(1)
					.setName(StaffPlus.get().message.colorize(StaffPlus.get().storage.customName))
					.addLore(StaffPlus.get().storage.customLoreList.toArray(loreArray))
					.build();
			
			player.getInventory().setItem(StaffPlus.get().storage.customSlot, custom);
		}
	}
	
	private static short getClayColorVanish(boolean vanished)
	{
		short data = 10;
		
		if(vanished)
		{
			data = 8;
		}
		
		return data;
	}
	
	private short getClayColor()
	{
		short data = 8;
		
		if(StaffPlus.get().storage.modeInvisible)
		{
			data = 10;
		}
		
		return data;
	}
}
