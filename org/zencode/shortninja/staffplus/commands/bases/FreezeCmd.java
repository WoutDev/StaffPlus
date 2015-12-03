package org.zencode.shortninja.staffplus.commands.bases;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.commands.Executor;

public class FreezeCmd implements Executor
{
	public void execute(CommandSender sender, String string, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(!StaffPlus.get().permission.hasPermission(player, StaffPlus.get().storage.modePermission) || !StaffPlus.get().storage.freezeEnabled)
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
					StaffPlus.get().freeze.toggle(player, targetPlayer);
				}else player.sendMessage(StaffPlus.get().message.playerNotFound());
			}else player.sendMessage(StaffPlus.get().message.invalidArguments());
		}
	}
}
