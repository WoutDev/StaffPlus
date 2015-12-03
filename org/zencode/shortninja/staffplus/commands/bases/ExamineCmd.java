package org.zencode.shortninja.staffplus.commands.bases;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.commands.Executors;
import org.zencode.shortninja.staffplus.guis.ExamineGUI;

public class ExamineCmd implements Executors
{
	public void execute(CommandSender sender, String string, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.modePermission) || !StaffPlus.get().storage.inventoryEnabled)
			{
				player.sendMessage(StaffPlus.get().message.noPermission());
				return;
			}
			
			if(args.length == 0)
			{
				player.sendMessage(StaffPlus.get().message.playerNotFound());
			}else if(args.length == 1)
			{
				Player targetPlayer = Bukkit.getPlayer(args[0]);
				
				if(targetPlayer != null)
				{
					new ExamineGUI(player, targetPlayer);
				}else player.sendMessage(StaffPlus.get().message.playerNotFound());
			}else player.sendMessage(StaffPlus.get().message.invalidArguments());
		}
	}
	
	private void sendHelp(Player player)
	{
		player.sendMessage(StaffPlus.get().message.longLine());
		
		player.sendMessage(StaffPlus.get().message.generalMessage("&b"));
		
		player.sendMessage(StaffPlus.get().message.longLine());
	}
}
