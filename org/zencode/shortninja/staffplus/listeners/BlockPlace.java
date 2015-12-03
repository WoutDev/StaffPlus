package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.methods.Mode;

public class BlockPlace implements Listener
{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onPlace(BlockPlaceEvent event)
	{
		Player player = event.getPlayer();
		
		if(!StaffPlus.get().storage.modeBlockManipulationDisabled || !Mode.active.contains(player.getName()))
		{
			return;
		}
		
		event.setCancelled(true);
	}
}
