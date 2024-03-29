package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.zencode.shortninja.staffplus.StaffPlus;

public class PlayerDropItem implements Listener
{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onDrop(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		
		if(!StaffPlus.get().storage.modeInventoryInteractionDisabled || !StaffPlus.get().mode.isActive(player.getName()))
		{
			return;
		}
		
		event.setCancelled(true);
	}
}
