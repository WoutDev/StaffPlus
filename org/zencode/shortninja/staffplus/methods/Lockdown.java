package org.zencode.shortninja.staffplus.methods;

import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Lockdown
{
	public static boolean open = true;
	
	public void toggle(Player player)
	{
		if(open)
		{
			open = false;
			player.sendMessage(StaffPlus.get().message.generalMessage("&bServer &7locked."));
		}else
		{
			open = true;
			player.sendMessage(StaffPlus.get().message.generalMessage("&bServer &7unlocked."));
		}
	}
	
	public void handleJoin(Player player)
	{
		if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.lockdownPermission))
		{
			player.kickPlayer(StaffPlus.get().message.generalMessage(StaffPlus.get().storage.lockdownMessage));
		}
	}
}
