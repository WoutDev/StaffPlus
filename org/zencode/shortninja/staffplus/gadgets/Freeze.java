package org.zencode.shortninja.staffplus.gadgets;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Freeze
{
	private static Set<String> active = new HashSet<String>();
	
	public boolean isActive(String playerName)
	{
		return active.contains(playerName);
	}
	
	public void toggle(Player player, Player targetPlayer)
	{
		if(active.contains(targetPlayer.getName()))
		{
			active.remove(targetPlayer.getName());
			targetPlayer.removePotionEffect(PotionEffectType.JUMP);
			targetPlayer.removePotionEffect(PotionEffectType.SLOW);
			
			targetPlayer.sendMessage(StaffPlus.get().message.generalMessage("&bYou are now unfrozen."));
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou unfroze &7" + targetPlayer.getName() + "&b."));
		}else
		{
			active.add(targetPlayer.getName());
			targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 128));
			targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 128));
			
			targetPlayer.sendMessage(StaffPlus.get().message.generalMessage(StaffPlus.get().storage.freezeMessage));
			player.sendMessage(StaffPlus.get().message.generalMessage("&bYou froze &7" + targetPlayer.getName() + "&b."));
		}
	}
}
