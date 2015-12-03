package org.zencode.shortninja.staffplus.methods;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.gadgets.StaffInventory;

public class Mode
{
	public static Set<String> active = new HashSet<String>();
	private static Map<String, Location> locations = new HashMap<String, Location>();
	
	public void toggle(Player player)
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
		active.add(player.getName());
		setPassive(player, true);
		
		player.sendMessage(StaffPlus.get().message.generalMessage("&bStaff mode &7enabled&b."));
	}
	
	public void disable(Player player)
	{
		if(!active.contains(player.getName()))
		{
			return;
		}
		
		active.remove(player.getName());
		setPassive(player, false);
		
		player.sendMessage(StaffPlus.get().message.generalMessage("&bStaff mode &7disabled&b."));
	}
	
	private void setPassive(Player player, boolean shouldEnable)
	{
		if(shouldEnable)
		{
			if(StaffPlus.get().storage.modeInvisible)
			{
				StaffPlus.get().vanish.enable(player);
			}
			
			if(StaffPlus.get().storage.modeFlight)
			{
				player.setAllowFlight(true);
			}
			
			if(StaffPlus.get().storage.modeOriginalLocation)
			{
				locations.put(player.getName(), player.getLocation());
			}
			
			new StaffInventory(player, true);
		}else
		{
			if(StaffPlus.get().storage.modeInvisible)
			{
				StaffPlus.get().vanish.disable(player);
			}
			
			if(StaffPlus.get().storage.modeFlight)
			{
				if(player.getGameMode() != GameMode.CREATIVE)
				{
					player.setAllowFlight(false);
				}
			}
			
			if(StaffPlus.get().storage.modeOriginalLocation)
			{
				Location location = locations.get(player.getName());
				player.teleport(location);
			}
			
			new StaffInventory(player, false);
		}
	}
}
