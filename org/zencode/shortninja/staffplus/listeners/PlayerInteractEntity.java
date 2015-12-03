package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.gadgets.CPS;
import org.zencode.shortninja.staffplus.guis.ExamineGUI;

public class PlayerInteractEntity implements Listener
{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onInteractEntity(PlayerInteractEntityEvent event)
	{
		Player player = event.getPlayer();
		
		if(!StaffPlus.get().mode.isActive(player.getName()))
		{
			return;
		}
		
		if(event.getRightClicked() instanceof Player)
		{
			Player targetPlayer = (Player) event.getRightClicked();
			ItemStack item = player.getItemInHand();
			
			if(item == null)
			{
				return;
			}
			
			if(isHoldingBlazeRod(item))
			{
				StaffPlus.get().freeze.toggle(player, targetPlayer);
			}else if(isHoldingWatch(item))
			{
				new CPS(player, targetPlayer);
			}else if(isHoldingChest(item))
			{
				new ExamineGUI(player, targetPlayer);
			}else if(isHoldingLead(item))
			{
				StaffPlus.get().follow.mount(player, targetPlayer);
			}
		}
	}
	
	private boolean isHoldingBlazeRod(ItemStack item)
	{
		boolean holding = false;
		
		if(item.getType() == Material.BLAZE_ROD && item.hasItemMeta())
		{
			ItemMeta itemMeta = item.getItemMeta();
			
			if(itemMeta.hasDisplayName())
			{
				if(itemMeta.getDisplayName().equals(StaffPlus.get().message.colorize("&bFreeze Player")))
				{
					holding = true;
				}
			}
		}
		
		return holding;
	}
	
	private boolean isHoldingWatch(ItemStack item)
	{
		boolean holding = false;
		
		if(item.getType() == Material.WATCH && item.hasItemMeta())
		{
			ItemMeta itemMeta = item.getItemMeta();
			
			if(itemMeta.hasDisplayName())
			{
				if(itemMeta.getDisplayName().equals(StaffPlus.get().message.colorize("&dCPS Checker")))
				{
					holding = true;
				}
			}
		}
		
		return holding;
	}
	
	private boolean isHoldingChest(ItemStack item)
	{
		boolean holding = false;
		
		if(item.getType() == Material.CHEST && item.hasItemMeta())
		{
			ItemMeta itemMeta = item.getItemMeta();
			
			if(itemMeta.hasDisplayName())
			{
				if(itemMeta.getDisplayName().equals(StaffPlus.get().message.colorize("&aInventory Inspect")))
				{
					holding = true;
				}
			}
		}
		
		return holding;
	}
	
	private boolean isHoldingLead(ItemStack item)
	{
		boolean holding = false;
		
		if(item.getType() == Material.LEASH && item.hasItemMeta())
		{
			ItemMeta itemMeta = item.getItemMeta();
			
			if(itemMeta.hasDisplayName())
			{
				if(itemMeta.getDisplayName().equals(StaffPlus.get().message.colorize("&eFollow Player")))
				{
					holding = true;
				}
			}
		}
		
		return holding;
	}
}
