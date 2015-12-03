package org.zencode.shortninja.staffplus.commands.bases;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.commands.Executors;

public class ChatCmd implements Executors
{
	public void execute(CommandSender sender, String string, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.chatPermission) || !StaffPlus.get().storage.chatEnabled)
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
				
				if(arg.equalsIgnoreCase("clear"))
				{
					StaffPlus.get().chat.clearChat(player);
				}else if(arg.equalsIgnoreCase("toggle"))
				{
					StaffPlus.get().chat.toggleChat(player);
				}else player.sendMessage(StaffPlus.get().message.invalidArguments());
			}else player.sendMessage(StaffPlus.get().message.invalidArguments());
		}
	}
	
	private void sendHelp(Player player)
	{
		player.sendMessage(StaffPlus.get().message.longLine());
		
		player.sendMessage(StaffPlus.get().message.generalMessage("&b/chat clear"));
		player.sendMessage(StaffPlus.get().message.generalMessage("&b/chat toggle"));
		
		player.sendMessage(StaffPlus.get().message.longLine());
	}
}
