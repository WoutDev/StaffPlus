package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.zencode.shortninja.staffplus.StaffPlus;

public class PlayerCommandPreprocess implements Listener
{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String message = event.getMessage();
		
		if(StaffPlus.get().storage.freezePreventChat && StaffPlus.get().freeze.isActive(player.getName()))
		{
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou cannot chat while frozen!"));
			event.setCancelled(true);
			return;
		}
		
		if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.blockCommandPermission))
		{
			return;
		}
		
		for(String blocked : StaffPlus.get().storage.blockedCommandsList)
		{
			if(message.equalsIgnoreCase(blocked) || message.equalsIgnoreCase("/" + blocked))
			{
				player.sendMessage(StaffPlus.get().message.generalMessage("&bStaff cannot use that command!"));
				event.setCancelled(true);
				break;
			}
		}
	}
}
