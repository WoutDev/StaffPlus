package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.methods.Mode;

public class PlayerDeath implements Listener
{
	@EventHandler(priority = EventPriority.NORMAL)
	public void onDeath(PlayerDeathEvent event)
	{
		Player player = (Player) event.getEntity();
		
		if(!Mode.active.contains(player.getName()))
		{
			return;
		}
		
		StaffPlus.get().mode.disable(player);
		event.getDrops().clear();
	}
}
