package org.zencode.shortninja.staffplus.util.shortninja;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.util.EnumUtils;

public class Sound
{
	public void attempt(Player player, String sound)
	{
		if(sound.equalsIgnoreCase("NONE"))
		{
			return;
		}
		
		boolean valid = EnumUtils.isValidEnum(org.bukkit.Sound.class, sound);
		
		if(valid)
		{
			org.bukkit.Sound realSound = org.bukkit.Sound.valueOf(sound);
			player.getWorld().playSound(player.getLocation(), realSound, 1, 0);
		}else
		{
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			console.sendMessage(StaffPlus.get().message.colorize("&4[Staff+] &cInvalid sound effect '" + sound+ "'!"));
		}
	}
}
