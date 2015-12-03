package org.zencode.shortninja.staffplus.methods;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.types.Report;

public class Alert
{
	private static Set<String> ignoreNameChange = new HashSet<String>();
	private static Set<String> ignoreMention = new HashSet<String>();
	private static Set<String> ignoreXray = new HashSet<String>();
	private static Set<String> ignoreReports = new HashSet<String>();
	
	public void addIgnoringNameChange(String playerName)
	{
		ignoreNameChange.add(playerName);
	}
	
	public void addIgnoringMention(String playerName)
	{
		ignoreMention.add(playerName);
	}
	
	public void addIgnoringXray(String playerName)
	{
		ignoreXray.add(playerName);
	}
	
	public void addIgnoringReports(String playerName)
	{
		ignoreReports.add(playerName);
	}
	
	public void removeIgnoringNameChange(String playerName)
	{
		ignoreNameChange.remove(playerName);
	}
	
	public void removeIgnoringMention(String playerName)
	{
		ignoreMention.remove(playerName);
	}
	
	public void removeIgnoringXray(String playerName)
	{
		ignoreXray.remove(playerName);
	}
	
	public void removeIgnoringReports(String playerName)
	{
		ignoreReports.remove(playerName);
	}
	
	public boolean isIgnoringNameChange(String playerName)
	{
		return ignoreNameChange.contains(playerName);
	}
	
	public boolean isIgnoringMention(String playerName)
	{
		return ignoreMention.contains(playerName);
	}
	
	public boolean isIgnoringXray(String playerName)
	{
		return ignoreXray.contains(playerName);
	}
	
	public boolean isIgnoringReports(String playerName)
	{
		return ignoreReports.contains(playerName);
	}
	
	public void notifyNameChange(String oldName, String newName)
	{
		if(!StaffPlus.get().storage.alertsNameEnabled)
		{
			return;
		}
		
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(!ignoreNameChange.contains(player.getName()) && StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.notifyPermission))
			{
				player.sendMessage(StaffPlus.get().message.generalMessage("&7" + oldName + " &bhas changed their name to &7" + newName + "&b!"));
				StaffPlus.get().sound.attempt(player, StaffPlus.get().storage.alertsSound);
			}
		}
	}
	
	public void notifyMention(Player player, Player targetPlayer)
	{
		if(!StaffPlus.get().storage.alertsMentionEnabled)
		{
			return;
		}
		
		if(!ignoreMention.contains(player.getName()) && StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.notifyPermission))
		{
			player.sendMessage(StaffPlus.get().message.generalMessage("&7" + targetPlayer.getName() + " &bhas mentioned you!"));
			StaffPlus.get().sound.attempt(player, StaffPlus.get().storage.alertsSound);
		}
	}
	
	public void notifyXray(Player targetPlayer, String block, int count)
	{
		if(!StaffPlus.get().storage.alertsXrayEnabled)
		{
			return;
		}
		
		String plural = count > 1 ? block.toLowerCase() + "s" : block.toLowerCase();
		
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(!ignoreXray.contains(player.getName()) && StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.notifyPermission))
			{
				player.sendMessage(StaffPlus.get().message.generalMessage("&7" + targetPlayer.getName() + " &bhas mined &7" + count + " " + plural + "&b."));
				StaffPlus.get().sound.attempt(player, StaffPlus.get().storage.alertsSound);
			}
		}
	}
	
	public void notifyReport(Report report)
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(!ignoreReports.contains(player.getName()) && StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.notifyPermission))
			{
				player.sendMessage(StaffPlus.get().message.reportsMessage("&7" + report.getName() + " &bhas been reported for &7" + report.getReason() + "&b."));
				StaffPlus.get().sound.attempt(player, StaffPlus.get().storage.alertsSound);
			}
		}
	}
}
