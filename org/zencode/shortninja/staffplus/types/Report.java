package org.zencode.shortninja.staffplus.types;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Report
{
	private static Map<String, Report> unresolvedReports = new HashMap<String, Report>();
	private String uuid;
	private String name;
	private String reason;
	
	public Report(){}
	
	public Report(String uuid, String name, String reason, boolean shouldNotify)
	{
		this.uuid = uuid;
		this.name = name;
		this.reason = reason;
		
		if(shouldNotify)
		{
			report();
		}
	}
	
	public Map<String, Report> getUnresolvedReports()
	{
		return unresolvedReports;
	}
	
	public void removeUnresolvedReport(String playerName)
	{
		unresolvedReports.remove(playerName);
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
	
	private void report()
	{
		Player player = Bukkit.getPlayer(name);
		User user;
		
		if(player == null)
		{
			return;
		}
		
		user = StaffPlus.get().user.getUser(player);
		user.addReport(this);
		
		unresolvedReports.put(name, this);
		
		StaffPlus.get().alert.notifyReport(this);
	}
}
