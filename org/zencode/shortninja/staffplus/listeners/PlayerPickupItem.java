package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.zencode.shortninja.staffplus.StaffPlus;

public class PlayerPickupItem implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPickup(PlayerPickupItemEvent event)
	{
		Player player = event.getPlayer();
		
		if(!StaffPlus.get().storage.modeInventoryInteractionDisabled || !StaffPlus.get().mode.isActive(player.getName()))
		{
			return;
		}
		
		event.setCancelled(true);
	}
}
