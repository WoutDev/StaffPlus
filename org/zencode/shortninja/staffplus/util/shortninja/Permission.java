package org.zencode.shortninja.staffplus.util.shortninja;

import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Permission
{
	public boolean hasPermission(Player player, String permission)
	{
		return player.hasPermission(permission) || player.hasPermission(StaffPlus.get().storage.wildCardPermission);
	}
}
