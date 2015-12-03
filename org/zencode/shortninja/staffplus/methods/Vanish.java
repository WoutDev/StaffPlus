package org.zencode.shortninja.staffplus.methods;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Vanish
{
	private static Set<String> active = new HashSet<String>();
	private static Set<String> busy = new HashSet<String>();
	
	public boolean isActive(String playerName)
	{
		return active.contains(playerName);
	}
	
	public boolean isBusy(String playerName)
	{
		return busy.contains(playerName);
	}
	
	public void addActive(String playerName)
	{
		active.add(playerName);
	}
	
	public void addBusy(String playerName)
	{
		busy.add(playerName);
	}
	
	public void removeActive(String playerName)
	{
		active.remove(playerName);
	}
	
	public void removeBusy(String playerName)
	{
		busy.remove(playerName);
	}
	
	public void totalVanish(Player player)
	{
		if(active.contains(player.getName()))
		{
			disable(player);
		}else
		{
			enable(player);
		}
	}
	
	public void enable(Player player)
	{
		for(Player hide : Bukkit.getOnlinePlayers())
		{
			hide.hidePlayer(player);
		}
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
		active.add(player.getName());
		player.sendMessage(StaffPlus.get().message.generalMessage("&bTotal vanish &7enabled&b."));
	}
	
	public void disable(Player player)
	{
		for(Player show : Bukkit.getOnlinePlayers())
		{
			show.showPlayer(player);
		}
		
		player.removePotionEffect(PotionEffectType.INVISIBILITY);
		active.remove(player.getName());
		player.sendMessage(StaffPlus.get().message.generalMessage("&bTotal vanish &7disabled&b."));
	}
	
	public void listVanish(Player player)
	{
		if(busy.contains(player.getName()))
		{
			busy.remove(player.getName());
			player.sendMessage(StaffPlus.get().message.generalMessage("&bList vanish &7disabled&b."));
		}else
		{
			busy.add(player.getName());
			player.sendMessage(StaffPlus.get().message.generalMessage("&bList vanish &7enabled&b."));
		}
	}
}
