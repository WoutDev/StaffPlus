package org.zencode.shortninja.staffplus.commands.bases;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.commands.Executor;

public class AlertsCmd implements Executor
{
	public void execute(CommandSender sender, String string, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.notifyPermission))
			{
				player.sendMessage(StaffPlus.get().message.noPermission());
				return;
			}
			
			if(args.length == 0)
			{
				sendHelp(player);
			}else if(args.length == 1)
			{
				String arg = args[0];
				
				if(arg.equalsIgnoreCase("name"))
				{
					handleName(player);
				}else if(arg.equalsIgnoreCase("mention"))
				{
					handleMention(player);
				}else if(arg.equalsIgnoreCase("xray"))
				{
					handleXray(player);
				}else if(arg.equalsIgnoreCase("reports"))
				{
					handleReports(player);
				}else player.sendMessage(StaffPlus.get().message.invalidArguments());
			}else player.sendMessage(StaffPlus.get().message.invalidArguments());
		}
	}
	
	private void sendHelp(Player player)
	{
		player.sendMessage(StaffPlus.get().message.longLine());
		
		player.sendMessage(StaffPlus.get().message.generalMessage("&b/alerts name"));
		player.sendMessage(StaffPlus.get().message.generalMessage("&b/alerts mention"));
		player.sendMessage(StaffPlus.get().message.generalMessage("&b/alerts xray"));
		player.sendMessage(StaffPlus.get().message.generalMessage("&b/alerts reports"));
		
		player.sendMessage(StaffPlus.get().message.longLine());
	}
	
	private void handleName(Player player)
	{
		if(StaffPlus.get().alert.isIgnoringNameChange(player.getName()))
		{
			StaffPlus.get().alert.removeIgnoringNameChange(player.getName());
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou will now &7receive &bname change alerts."));
		}else
		{
			StaffPlus.get().alert.addIgnoringNameChange(player.getName());
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou will now &7ignore &bname change alerts."));
		}
	}
	
	private void handleMention(Player player)
	{
		if(StaffPlus.get().alert.isIgnoringMention(player.getName()))
		{
			StaffPlus.get().alert.removeIgnoringMention(player.getName());
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou will now &7receive &bmention alerts."));
		}else
		{
			StaffPlus.get().alert.addIgnoringMention(player.getName());
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou will now &7ignore &bmention alerts."));
		}
	}
	
	private void handleXray(Player player)
	{
		if(StaffPlus.get().alert.isIgnoringXray(player.getName()))
		{
			StaffPlus.get().alert.removeIgnoringXray(player.getName());
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou will now &7receive &bX-Ray alerts."));
		}else
		{
			StaffPlus.get().alert.removeIgnoringXray(player.getName());
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou will now &7ignore &bX-Ray alerts."));
		}
	}
	
	private void handleReports(Player player)
	{
		if(StaffPlus.get().alert.isIgnoringReports(player.getName()))
		{
			StaffPlus.get().alert.removeIgnoringReports(player.getName());
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou will now &7receive &breport alerts."));
		}else
		{
			StaffPlus.get().alert.addIgnoringReports(player.getName());
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou will now &7ignore &breport alerts."));
		}
	}
}
