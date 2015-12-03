package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.gadgets.StaffInventory;
import org.zencode.shortninja.staffplus.guis.ReportGUI;
import org.zencode.shortninja.staffplus.methods.Mode;

public class PlayerInteract implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Action click = event.getAction();
		
		if(StaffPlus.get().cps.isTesting(player.getName()) && isLeftClick(click))
		{
			int clicks = StaffPlus.get().cps.getClicks(player.getName()) + 1;
			
			StaffPlus.get().cps.updateClicks(player.getName(), clicks);
			return;
		}
		
		if(Mode.active.contains(player.getName()) && isRightClick(click))
		{
			ItemStack item = player.getItemInHand();
			
			if(item == null)
			{
				return;
			}
			
			if(isHoldingCompass(item))
			{
				StaffPlus.get().launch.launch(player);
			}else if(isHoldingEyeOfEnder(item))
			{
				StaffPlus.get().teleport.random(player);
				event.setCancelled(true);
			}else if(isHoldingClay(item))
			{
				StaffInventory.toggleVanish(player);
			}else if(isHoldingPaper(item))
			{
				new ReportGUI(player);
			}else if(isHoldingCustom(item))
			{
				Bukkit.dispatchCommand(player, StaffPlus.get().storage.customCommand);
			}else if(isHoldingChest(item))
			{
				event.setCancelled(true);
				player.updateInventory();
			}
		}
	}
	
	private boolean isLeftClick(Action click)
	{
		if(click == Action.LEFT_CLICK_AIR || click == Action.LEFT_CLICK_BLOCK)
		{
			return true;
		}
		
		return false;
	}
	
	private boolean isRightClick(Action click)
	{
		if(click == Action.RIGHT_CLICK_AIR || click == Action.RIGHT_CLICK_BLOCK)
		{
			return true;
		}
		
		return false;
	}
	
	private boolean isHoldingCompass(ItemStack item)
	{
		boolean holding = false;
		
		if(item.getType() == Material.COMPASS && item.hasItemMeta())
		{
			ItemMeta itemMeta = item.getItemMeta();
			
			if(itemMeta.hasDisplayName())
			{
				if(itemMeta.getDisplayName().equals(StaffPlus.get().message.colorize("&4Compass Launch")))
				{
					holding = true;
				}
			}
		}
		
		return holding;
	}
	
	private boolean isHoldingEyeOfEnder(ItemStack item)
	{
		boolean holding = false;
		
		if(item.getType() == Material.EYE_OF_ENDER && item.hasItemMeta())
		{
			ItemMeta itemMeta = item.getItemMeta();
			
			if(itemMeta.hasDisplayName())
			{
				if(itemMeta.getDisplayName().equals(StaffPlus.get().message.colorize("&3Random Teleport")))
				{
					holding = true;
				}
			}
		}
		
		return holding;
	}
	
	private boolean isHoldingClay(ItemStack item)
	{
		boolean holding = false;
		
		if(item.getType() == Material.INK_SACK && item.hasItemMeta())
		{
			ItemMeta itemMeta = item.getItemMeta();
			
			if(itemMeta.hasDisplayName())
			{
				if(itemMeta.getDisplayName().equals(StaffPlus.get().message.colorize("&cToggle Vanish")))
				{
					holding = true;
				}
			}
		}
		
		return holding;
	}
	
	private boolean isHoldingPaper(ItemStack item)
	{
		boolean holding = false;
		
		if(item.getType() == Material.PAPER && item.hasItemMeta())
		{
			ItemMeta itemMeta = item.getItemMeta();
			
			if(itemMeta.hasDisplayName())
			{
				if(itemMeta.getDisplayName().equals(StaffPlus.get().message.colorize("&6Reports GUI")))
				{
					holding = true;
				}
			}
		}
		
		return holding;
	}
	
	private boolean isHoldingCustom(ItemStack item)
	{
		boolean holding = false;
		
		if(!StaffPlus.get().inventory.isActualBlock(StaffPlus.get().storage.customItem))
		{
			holding = false;
		}else
		{
			Material custom = Material.matchMaterial(StaffPlus.get().storage.customItem);
			
			if(item.getType() == custom&& item.hasItemMeta())
			{
				ItemMeta itemMeta = item.getItemMeta();
				
				if(itemMeta.hasDisplayName())
				{
					if(itemMeta.getDisplayName().equals(StaffPlus.get().message.colorize(StaffPlus.get().storage.customName)))
					{
						holding = true;
					}
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
}
