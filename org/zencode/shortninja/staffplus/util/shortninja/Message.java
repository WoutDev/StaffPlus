package org.zencode.shortninja.staffplus.util.shortninja;

import org.zencode.shortninja.staffplus.StaffPlus;
import org.bukkit.ChatColor;

public class Message
{
	public String colorize(String msg)
	{
		String coloredMsg = ChatColor.translateAlternateColorCodes('&', msg);
		return coloredMsg;
	}
	
	public String generalMessage(String msg)
	{
		return colorize(StaffPlus.get().storage.generalPrefix + " " + msg);
	}
	
	public String reportsMessage(String msg)
	{
		return colorize(StaffPlus.get().storage.reportsPrefix + " " + msg);
	}
	
	public String warningsMessage(String msg)
	{
		return colorize(StaffPlus.get().storage.warningsPrefix + " " + msg);
	}
	
	public String longLine()
	{
		return colorize("&7&m------------------------------------------------");
	}
	
	public String staffChatMessage(String msg)
	{
		return colorize(StaffPlus.get().storage.staffChatPrefix + " " + msg);
	}
	
	public String noPermission()
	{
		return generalMessage("&cYou do not have permission!");
	}
	
	public String playerNotFound()
	{
		return generalMessage("&cPlayer not found!");
	}
	
	public String invalidArguments()
	{
		return generalMessage("&cInvalid arguments!");
	}
}
