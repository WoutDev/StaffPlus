package org.zencode.shortninja.staffplus.commands.bases;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.commands.Executors;

public class StaffCmd implements Executors
{
	public void execute(CommandSender sender, String string, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(args.length == 0)
			{
				if(StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.modePermission))
				{
					StaffPlus.get().mode.toggle(player);
				}else player.sendMessage(StaffPlus.get().message.noPermission());
			}else if(args.length == 1)
			{
				String arg = args[0];
				
				if(arg.equalsIgnoreCase("list"))
				{
					StaffPlus.get().staff.sendList(player);
				}else player.sendMessage(StaffPlus.get().message.invalidArguments());
			}else player.sendMessage(StaffPlus.get().message.invalidArguments());
		}
	}
}
