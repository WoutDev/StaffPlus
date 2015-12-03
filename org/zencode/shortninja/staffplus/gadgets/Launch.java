package org.zencode.shortninja.staffplus.gadgets;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Launch
{
	public void launch(Player player)
	{
		Vector vector = player.getLocation().getDirection();
		
		vector = vector.multiply(StaffPlus.get().storage.compassSpeed);
		player.setVelocity(vector);
	}
}
