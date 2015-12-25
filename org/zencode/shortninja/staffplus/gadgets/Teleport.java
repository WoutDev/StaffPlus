package org.zencode.shortninja.staffplus.gadgets;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Teleport
{
	public void random(Player player)
	{
		int players = Bukkit.getOnlinePlayers().size();
		
		if(players > 1)
		{
            Random random = new Random();
			int current = random.nextInt(Bukkit.getOnlinePlayers().size());
			int i = 0;
			for(Player target : Bukkit.getOnlinePlayers())
			{
				if(players >= 3)
				{
					i++;
				}else if(!player.getName().equals(target.getName()) && players < 3)
				{
					player.teleport(target);
				}

				if(i == current)
				{
					if(!player.getName().equals(target.getName()))
					{
						player.teleport(target);
					}
				}
			}
		}else player.sendMessage(StaffPlus.get().message.generalMessage("&bNot enough players online."));
	}
}
