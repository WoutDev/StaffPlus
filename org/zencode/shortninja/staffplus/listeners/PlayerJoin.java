package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.zencode.shortninja.staffplus.StaffPlus;

public class PlayerJoin implements Listener
{
	@EventHandler(priority = EventPriority.NORMAL)
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		if(StaffPlus.get().storage.modeOnLogin && StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.modePermission))
		{
			StaffPlus.get().mode.enable(player);
		}
		
		StaffPlus.get().management.attemptLoad(player);
	}
}
