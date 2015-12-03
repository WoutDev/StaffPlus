package org.zencode.shortninja.staffplus.commands.bases;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.commands.Executor;
import org.zencode.shortninja.staffplus.gadgets.StaffInventory;
import org.zencode.shortninja.staffplus.methods.Mode;

public class VanishCmd implements Executor
{
	public void execute(CommandSender sender, String string, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.vanishPermission) || !StaffPlus.get().storage.vanishEnabled)
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
				
				if(arg.equalsIgnoreCase("total"))
				{
					if(StaffPlus.get().mode.isActive(player.getName()))
					{
						StaffInventory.toggleVanish(player);
					}else StaffPlus.get().vanish.totalVanish(player);
				}else if(arg.equalsIgnoreCase("list"))
				{
					StaffPlus.get().vanish.listVanish(player);
				}else player.sendMessage(StaffPlus.get().message.invalidArguments());
			}else player.sendMessage(StaffPlus.get().message.invalidArguments());
		}
	}
	
	private void sendHelp(Player player)
	{
		player.sendMessage(StaffPlus.get().message.longLine());
		
		player.sendMessage(StaffPlus.get().message.generalMessage("&b/v total"));
		player.sendMessage(StaffPlus.get().message.generalMessage("&b/v list"));
		
		player.sendMessage(StaffPlus.get().message.longLine());
	}
}
