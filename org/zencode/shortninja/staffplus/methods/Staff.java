package org.zencode.shortninja.staffplus.methods;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Staff
{
	public static Set<String> active = new HashSet<String>();
	
	public void toggleChat(Player player)
	{
		if(active.contains(player.getName()))
		{
			active.remove(player.getName());
			player.sendMessage(StaffPlus.get().message.staffChatMessage("&bStaff chat &7disabled&b."));
		}else
		{
			active.add(player.getName());
			player.sendMessage(StaffPlus.get().message.staffChatMessage("&bStaff chat &7enabled&b."));
		}
	}
	
	public void sendChat(Player player, String message)
	{
		message = "&b" + player.getName() + " &8» &7" + message;
		
		for(Player targetPlayer : Bukkit.getOnlinePlayers())
		{
			if(StaffPlus.get().permission.hasPermission(targetPlayer, StaffPlus.get().storage.staffChatPermission))
			{
				targetPlayer.sendMessage(StaffPlus.get().message.staffChatMessage(message));
			}
		}
	}
	
	public void sendList(Player player)
	{
        player.sendMessage(StaffPlus.get().message.longLine());
        
        for(String staffOffline : StaffPlus.get().storage.staffList)
        {
        	Player staffOnline = Bukkit.getPlayer(staffOffline);
        	
        	if(staffOnline != null)
        	{
        		if(StaffPlus.get().vanish.isBusy(staffOnline.getName()))
        		{
        			player.sendMessage(StaffPlus.get().message.generalMessage("&b" + staffOnline.getName() + " &e&l•"));
        		}else player.sendMessage(StaffPlus.get().message.generalMessage("&b" + staffOnline.getName() + " &a&l•"));
        	}else player.sendMessage(StaffPlus.get().message.generalMessage("&b" + staffOffline + " &4&l•"));
        }
        
        player.sendMessage(StaffPlus.get().message.longLine());
	}
}
