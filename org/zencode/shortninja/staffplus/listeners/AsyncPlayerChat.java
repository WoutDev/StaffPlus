package org.zencode.shortninja.staffplus.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.guis.WarningGUI;
import org.zencode.shortninja.staffplus.types.Warning;

public class AsyncPlayerChat implements Listener
{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		String message = event.getMessage();
		
		if(!StaffPlus.get().chat.isAllowedToChat() && !StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.chatPermission))
		{
			player.sendMessage(StaffPlus.get().message.generalMessage("&bChat is currently disabled!"));
			event.setCancelled(true);
			return;
		}
		
		if(StaffPlus.get().storage.freezePreventChat && StaffPlus.get().freeze.isActive(player.getName()))
		{
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou cannot chat while frozen!"));
			event.setCancelled(true);
			return;
		}
		
		if(StaffPlus.get().staff.isActive(player.getName()))
		{
			StaffPlus.get().staff.sendChat(player, message);
			event.setCancelled(true);
			return;
		}
		
		if(StaffPlus.get().warningGUI.isWaitingForReason(player.getName()))
		{
			completeWarningReason(player, message);
			event.setCancelled(true);
			return;
		}
		
		checkForMentions(player, message.split(" "));
	}
	
	private void completeWarningReason(Player player, String reason)
	{
		WarningGUI warning = StaffPlus.get().warningGUI.getWaitingForReason(player.getName());
		
		new Warning(warning.getUuid(), warning.getName(), reason, true);
		player.sendMessage(StaffPlus.get().message.reportsMessage("&bWarned &7" + warning.getName() + "&b for &7" + reason + "&b."));
		StaffPlus.get().warningGUI.getWaitingForReason(player.getName());
	}
	
	private void checkForMentions(Player player, String[] words)
	{
		for(Player targetPlayer : Bukkit.getOnlinePlayers())
		{
			for(String word : words)
			{
				if(word.equalsIgnoreCase(targetPlayer.getName()))
				{
					if(player.getName().equals(targetPlayer.getName()))
					{
						continue;
					}
					
					if(!StaffPlus.get().permission.hasPermission(targetPlayer, StaffPlus.get().storage.notifyPermission) || StaffPlus.get().alert.isIgnoringMention(targetPlayer.getName()))
					{
						continue;
					}
					
					StaffPlus.get().alert.notifyMention(targetPlayer, player);
				}
			}
		}
	}
}
