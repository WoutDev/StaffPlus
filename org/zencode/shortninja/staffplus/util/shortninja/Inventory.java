package org.zencode.shortninja.staffplus.util.shortninja;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.util.EnumUtils;

public class Inventory
{
	public void clear(Player player)
	{
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
		
		player.getInventory().clear();
	}
	
	public boolean isActualBlock(String string)
	{
		boolean valid = EnumUtils.isValidEnum(Material.class, string);
		
		if(!valid)
		{
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			console.sendMessage(StaffPlus.get().message.colorize("&4[Staff+] &cInvalid block '" + string + "'!"));
		}
		
		return valid;
	}
}
