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
			int dice = random.nextInt(Bukkit.getOnlinePlayers().size());
			Player randomPlayer = getRandomPlayer(player, dice);
			
			if(randomPlayer != null)
			{
				player.teleport(randomPlayer.getLocation());
			}
		}else player.sendMessage(StaffPlus.get().message.generalMessage("&bNot enough players online."));
	}
	
	private Player getRandomPlayer(Player player, int dice)
	{
		Player targetPlayer = null;
		int players = Bukkit.getOnlinePlayers().size();
		
		for(int i = 0; i < players; i++)
		{
			Player potentialPlayer = (Player) Bukkit.getOnlinePlayers().toArray()[i];
			
			if(players >= 3)
			{
				i++;
			}else if(!player.getName().equals(potentialPlayer.getName()) && players < 3)
			{
				potentialPlayer = targetPlayer;
				break;
			}

			if(i == dice)
			{
				if(!player.getName().equals(potentialPlayer.getName()))
				{
					potentialPlayer = targetPlayer;
					break;
				}
			}
		}
		
		return targetPlayer;
	}
}
