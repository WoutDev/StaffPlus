package org.zencode.shortninja.staffplus.types;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Warning
{
	private String uuid;
	private String name;
	private String reason;
	
	public Warning(String uuid, String name, String reason, boolean shouldNotify)
	{
		this.uuid = uuid;
		this.name = name;
		this.reason = reason;
		
		if(shouldNotify)
		{
			warn();
		}
	}
	
	public String getUuid()
	{
		return uuid;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getReason()
	{
		return reason;
	}
	
	private void warn()
	{
		Player player = Bukkit.getPlayer(name);
		User user;
		
		if(player == null)
		{
			return;
		}
		
		user = StaffPlus.get().user.getUser(player);
		user.addWarning(this);
		
		player.sendMessage(StaffPlus.get().message.warningsMessage("&bYou were warned for &7" + reason + "&b."));
		
		StaffPlus.get().sound.attempt(player, StaffPlus.get().storage.warningsSoundEffect);
		
		if(shouldBan())
		{
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), StaffPlus.get().storage.warningsBanCommand);
		}
	}
	
	private boolean shouldBan()
	{
		boolean shouldBan = false;
		int maximum = StaffPlus.get().storage.warningsMaximum;
		
		if(maximum != 0)
		{
			Player player = Bukkit.getPlayer(name);
			
			if(player != null)
			{
				User user = StaffPlus.get().user.getUser(player);
				
				shouldBan = user.getWarnings() >= maximum;
			}
		}
		
		return shouldBan;
	}
}
