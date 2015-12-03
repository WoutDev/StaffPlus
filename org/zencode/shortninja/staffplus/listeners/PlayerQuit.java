package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.data.Save;
import org.zencode.shortninja.staffplus.types.User;

public class PlayerQuit implements Listener
{
	/*
	 * I'm going to update the data structure to handle player data saving
	 * a bit later. I made this data structure when I was a less experienced.
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		User user = StaffPlus.get().user.getUser(player);
		
		StaffPlus.get().mode.disable(player);
		new Save(user);
		clearCollections(user);
	}
	
	private void clearCollections(User user)
	{
		StaffPlus.get().cps.removeTesting(user.getName());
		StaffPlus.get().vanish.removeActive(user.getName());
		StaffPlus.get().vanish.removeBusy(user.getName());
		StaffPlus.get().report.removeUnresolvedReport(user.getName());
		StaffPlus.get().user.removeUser(user);
	}
}
