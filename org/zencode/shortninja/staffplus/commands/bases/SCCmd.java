package org.zencode.shortninja.staffplus.commands.bases;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.commands.Executors;

public class SCCmd implements Executors
{
	public void execute(CommandSender sender, String string, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.modePermission) || !StaffPlus.get().storage.staffChatEnabled)
			{
				player.sendMessage(StaffPlus.get().message.noPermission());
				return;
			}
			
			if(args.length == 0)
			{
				StaffPlus.get().staff.toggleChat(player);
			}else if(args.length >= 1)
			{
				StaffPlus.get().staff.sendChat(player, buildMessage(args));
			}
		}
	}
	
	private String buildMessage(String[] words)
	{
		StringBuilder builder = new StringBuilder();
		
		for(String word : words)
		{
			builder.append(word).append(" ");
		}
		
		return builder.toString().trim();
	}
}
