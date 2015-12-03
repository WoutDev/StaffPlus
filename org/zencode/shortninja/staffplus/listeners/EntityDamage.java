package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.zencode.shortninja.staffplus.StaffPlus;

public class EntityDamage implements Listener
{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();
			
			if(StaffPlus.get().storage.freezeInvincible && StaffPlus.get().freeze.isActive(player.getName()))
			{
				event.setCancelled(true);
				return;
			}
			
			if(StaffPlus.get().storage.modeInvincible && StaffPlus.get().mode.isActive(player.getName()))
			{
				event.setCancelled(true);
				return;
			}
		}
	}
}
