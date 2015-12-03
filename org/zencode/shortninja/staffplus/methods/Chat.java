package org.zencode.shortninja.staffplus.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Chat
{
	private static boolean allowedToChat = true;
	
	public boolean isAllowedToChat()
	{
		return allowedToChat;
	}
	
	public void clearChat(Player player)
	{
		for(int i = 0; i < 100; i++)
		{
			Bukkit.broadcastMessage(" ");
		}
		
		Bukkit.broadcastMessage(StaffPlus.get().message.generalMessage("&bChat cleared by &7" + player.getName() + "&b."));
	}
	
	public void toggleChat(Player player)
	{
		if(allowedToChat)
		{
			allowedToChat = false;
			Bukkit.broadcastMessage(StaffPlus.get().message.generalMessage("&bChat &7disabled &bby &7" + player.getName() + "&b."));
		}else
		{
			allowedToChat = true;
			Bukkit.broadcastMessage(StaffPlus.get().message.generalMessage("&bChat &7enabled &bby &7" + player.getName() + "&b."));
		}
	}
}
