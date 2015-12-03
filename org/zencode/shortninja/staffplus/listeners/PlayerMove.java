package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.zencode.shortninja.staffplus.StaffPlus;

public class PlayerMove implements Listener
{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
	    Location t = event.getTo();
	    Location f = event.getFrom();
	    
	    if(t.getBlockX() != f.getBlockX() || t.getBlockY() != f.getBlockY() || t.getBlockZ() != f.getBlockZ())
	    {
	    	if(!StaffPlus.get().freeze.isActive(player.getName()))
	    	{
	    		return;
	    	}
	    	
			player.teleport(f);
	    }
	}
}
