package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.zencode.shortninja.staffplus.StaffPlus;

public class EntityDamageByEntity implements Listener
{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onAttack(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			if(!(event.getEntity() instanceof Player))
			{
				return;
			}
			
			Player damaged = (Player) event.getEntity();
			
			if(StaffPlus.get().storage.freezeInvincible && StaffPlus.get().freeze.isActive(damaged.getName()))
			{
				event.setCancelled(true);
				return;
			}
			
			if(StaffPlus.get().storage.modeInvincible && StaffPlus.get().mode.isActive(damaged.getName()))
			{
				event.setCancelled(true);
				return;
			}
		}
	}
}
