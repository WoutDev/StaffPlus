package org.zencode.shortninja.staffplus.gadgets;

import org.bukkit.entity.Player;

public class Follow
{
	public void mount(Player player, Player targetPlayer)
	{
		if(player.getVehicle() != null)
		{
			player.getVehicle().eject();
		}
		
		targetPlayer.setPassenger(player);
	}
}
