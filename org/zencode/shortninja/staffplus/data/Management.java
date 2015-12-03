package org.zencode.shortninja.staffplus.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.types.User;

public class Management
{
	public void attemptLoad(Player player)
	{
		String uuid = player.getUniqueId().toString();
    	if(!isNew(uuid))
    	{
    		new User(player.getName(), uuid);
    		new Load(player, true);
    	}else new Load(player, false);
	}
	
	public void openServer()
	{
        for(Player player : Bukkit.getOnlinePlayers())
        {
        	attemptLoad(player);
        }
	}
	
	public void closeServer()
	{
        for(User user : StaffPlus.get().user.getUsers())
        {
        	new Save(user);
        }
	}
	
	private static boolean isNew(String uuid)
	{
		return StaffPlus.get().data.getData().contains(uuid);
	}
}
