package org.zencode.shortninja.staffplus.commands.bases;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.commands.Executor;
import org.zencode.shortninja.staffplus.types.Report;
import org.zencode.shortninja.staffplus.types.User;

public class ReportCmd implements Executor
{
	private static Map<String, Long> lastUse = new HashMap<String, Long>();
	private final long COOLDOWN = StaffPlus.get().storage.reportsCooldown;
	
	public void execute(CommandSender sender, String string, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(!StaffPlus.get().storage.reportsEnabled)
			{
				player.sendMessage(StaffPlus.get().message.noPermission());
				return;
			}
			
			if(args.length == 0)
			{
				if(StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.reportPermission))
				{
					sendHelp(player);
				}else player.sendMessage(StaffPlus.get().message.playerNotFound());
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
					}else if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.reportPermission))
					{
						player.sendMessage(StaffPlus.get().message.noPermission());
						return;
					}
					
					sendReports(player, StaffPlus.get().user.getUser(targetPlayer));
				}else if(arg.equalsIgnoreCase("clear"))
				{
					if(targetPlayer == null)
					{
						player.sendMessage(StaffPlus.get().message.playerNotFound());
						return;
					}else if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.reportPermission))
					{
						player.sendMessage(StaffPlus.get().message.noPermission());
						return;
					}
					
					User user = StaffPlus.get().user.getUser(targetPlayer);
					
					user.clearReports();
					StaffPlus.get().report.removeUnresolvedReport(targetPlayer.getName());
					player.sendMessage(StaffPlus.get().message.reportsMessage("&bReports cleared for &7" + targetPlayer.getName() + "&b."));
				}else
				{
					long last = lastUse.containsKey(player.getName()) ? lastUse.get(player.getName()) : 0;
					long now = System.currentTimeMillis();
					arg = args[1];
					targetPlayer = Bukkit.getPlayer(args[0]);
					
					if(now - last < COOLDOWN)
					{
						 long remaining = COOLDOWN - (now - last);
						 player.sendMessage(timeRemaining(remaining));
						 return;
					}
					
					if(targetPlayer == null)
					{
						player.sendMessage(StaffPlus.get().message.playerNotFound());
						return;
					}
					
					String reason = buildReason(args);
					String uuid = targetPlayer.getUniqueId().toString();
					
					new Report(uuid, targetPlayer.getName(), reason, true);
					lastUse.put(player.getName(), now);
					player.sendMessage(StaffPlus.get().message.reportsMessage("&bReported &7" + targetPlayer.getName() + "&b for &7" + reason + "&b."));
				}
			}else player.sendMessage(StaffPlus.get().message.invalidArguments());
		}
	}
	
	private void sendHelp(Player player)
	{
		player.sendMessage(StaffPlus.get().message.longLine());
		
		player.sendMessage(StaffPlus.get().message.reportsMessage("&b/report get &7[player]"));
		player.sendMessage(StaffPlus.get().message.reportsMessage("&b/report clear &7[player]"));
		player.sendMessage(StaffPlus.get().message.reportsMessage("&b/report &7[player] [reason]"));
		
		player.sendMessage(StaffPlus.get().message.longLine());
	}
	
	private void sendReports(Player player, User user)
	{
		player.sendMessage(StaffPlus.get().message.longLine());
		player.sendMessage(StaffPlus.get().message.reportsMessage("&7" + user.getName() + " &bhas &7" + user.getReports() + " &breports."));
		
		for(int i = 0; i < user.getReports(); i++)
		{
			Report report = user.reports()[i];
			int count = i + 1;
			
			player.sendMessage(StaffPlus.get().message.reportsMessage("&b" + count + ": &7" + report.getReason()));
		}
		
		player.sendMessage(StaffPlus.get().message.longLine());
	}
	
	private String buildReason(String[] args)
	{
		StringBuilder builder = new StringBuilder();
		
		for(int i = 1; i < args.length; i++)
		{
			builder.append(args[i]).append(" ");
		}
		
		return builder.toString().trim();
	}
	
	private String timeRemaining(long remaining)
	{
		return StaffPlus.get().message.reportsMessage("&bYou cannot report for &7"
				 + String.format("%d &bseconds.", TimeUnit.MILLISECONDS.toSeconds(remaining)));
	}
}
