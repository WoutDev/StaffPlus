package org.zencode.shortninja.staffplus.commands.bases;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.commands.Executor;
import org.zencode.shortninja.staffplus.types.User;
import org.zencode.shortninja.staffplus.types.Warning;

public class WarnCmd implements Executor
{
	public void execute(CommandSender sender, String string, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(!StaffPlus.get().storage.warningsEnabled || !StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.warnPermission))
			{
				player.sendMessage(StaffPlus.get().message.noPermission());
				return;
			}
			
			
			if(args.length == 0)
			{
				sendHelp(player);
			}else if(args.length == 1)
			{
				player.sendMessage(StaffPlus.get().message.invalidArguments());
			}else if(args.length >= 2)
			{
				String arg = args[0];
				Player targetPlayer = Bukkit.getPlayer(args[1]);
				
				if(arg.equalsIgnoreCase("get"))
				{
					if(targetPlayer == null)
					{
						player.sendMessage(StaffPlus.get().message.playerNotFound());
						return;
					}else if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.warnPermission))
					{
						player.sendMessage(StaffPlus.get().message.noPermission());
						return;
					}
					
					sendWarnings(player, StaffPlus.get().user.getUser(targetPlayer));
				}else if(arg.equalsIgnoreCase("clear"))
				{
					if(targetPlayer == null)
					{
						player.sendMessage(StaffPlus.get().message.playerNotFound());
						return;
					}else if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.warnPermission))
					{
						player.sendMessage(StaffPlus.get().message.noPermission());
						return;
					}
					
					User user = StaffPlus.get().user.getUser(targetPlayer);
					
					user.clearWarnings();
					player.sendMessage(StaffPlus.get().message.warningsMessage("&bWarnings cleared for &7" + targetPlayer.getName() + "&b."));
				}else
				{
					targetPlayer = Bukkit.getPlayer(args[0]);
					arg = args[1];
					
					if(targetPlayer == null)
					{
						player.sendMessage(StaffPlus.get().message.playerNotFound());
						return;
					}
					
					String reason = buildWarning(args);
					String uuid = targetPlayer.getUniqueId().toString();
					new Warning(uuid, targetPlayer.getName(), reason, true);
					player.sendMessage(StaffPlus.get().message.reportsMessage("&bWarned &7" + targetPlayer.getName() + "&b for &7" + reason + "&b."));
				}
			}else player.sendMessage(StaffPlus.get().message.invalidArguments());
		}
	}
	
	private void sendHelp(Player player)
	{
		player.sendMessage(StaffPlus.get().message.longLine());
		
		player.sendMessage(StaffPlus.get().message.warningsMessage("&b/warn get &7[player]"));
		player.sendMessage(StaffPlus.get().message.warningsMessage("&b/warn clear &7[player]"));
		player.sendMessage(StaffPlus.get().message.warningsMessage("&b/warn &7[player] [reason]"));
		
		player.sendMessage(StaffPlus.get().message.longLine());
	}
	
	private void sendWarnings(Player player, User user)
	{
		
		player.sendMessage(StaffPlus.get().message.longLine());
		player.sendMessage(StaffPlus.get().message.warningsMessage("&7" + user.getName() + " &bhas &7" + user.getWarnings() + " &bwarnings."));
		
		for(int i = 0; i < user.getWarnings(); i++)
		{
			Warning warning = user.warnings()[i];
			int count = i + 1;
			
			player.sendMessage(StaffPlus.get().message.warningsMessage("&b" + count + ": &7" + warning.getReason()));
		}
		
		player.sendMessage(StaffPlus.get().message.longLine());
	}
	
	private String buildWarning(String[] args)
	{
		StringBuilder builder = new StringBuilder();
		
		for(int i = 1; i < args.length; i++)
		{
			builder.append(args[i]).append(" ");
		}
		
		return builder.toString().trim();
	}
}
