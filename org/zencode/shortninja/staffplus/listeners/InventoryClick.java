package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.guis.WarningGUI;

public class InventoryClick implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		Inventory inventory = event.getInventory();
		
		if(StaffPlus.get().storage.modeInventoryInteractionDisabled && StaffPlus.get().mode.isActive(player.getName()))
		{
			event.setCancelled(true);
		}
		
		if(event.getCurrentItem() == null)
		{
			return;
		}
		
		if(isExamineGUI(inventory))
		{
			ItemStack item = event.getCurrentItem();
			Material type = item.getType();
			String offlinePlayer = inventory.getName().substring(12);
			Player targetPlayer = Bukkit.getPlayer(offlinePlayer);
			
			if(item != null && targetPlayer != null)
			{
				handleExamineGUI(type, player, targetPlayer);
			}
			
			event.setCancelled(true);
			player.closeInventory();
		}else if(isReportGUI(inventory))
		{
			ItemStack item = event.getCurrentItem();
			
			if(item.getItemMeta() != null)
			{
				Material type = item.getType();
				String offlinePlayer = item.getItemMeta().getDisplayName().substring(2);
				Player targetPlayer = Bukkit.getPlayer(offlinePlayer);
				
				if(type != null && targetPlayer != null)
				{
					handleReportGUI(type, player, targetPlayer);
				}
			}
			
			event.setCancelled(true);
			player.closeInventory();
		}
	}
	
	private boolean isExamineGUI(Inventory inventory)
	{
		return inventory.getName().startsWith(StaffPlus.get().message.colorize("&c&lExamine "));
	}
	
	private boolean isReportGUI(Inventory inventory)
	{
		return inventory.getName().equals(StaffPlus.get().message.colorize("&c&lUnresolved Reports"));
	}
	
	private void handleExamineGUI(Material item, Player player, Player targetPlayer)
	{
		switch(item)
		{
			case PAPER:
				new WarningGUI(player, targetPlayer);
				break;
			case BLAZE_ROD:
				StaffPlus.get().freeze.toggle(player, targetPlayer);
				break;	
			default:
				break;
		}
	}
	
	private void handleReportGUI(Material item, Player player, Player targetPlayer)
	{
		switch(item)
		{
			case SKULL_ITEM:
				player.teleport(targetPlayer);
				StaffPlus.get().report.removeUnresolvedReport(targetPlayer.getName());
				break;
			default:
				break;
		}
	}
}
