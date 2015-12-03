package org.zencode.shortninja.staffplus.types;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

public class User
{
	private static Set<User> users = new HashSet<User>();
	private String uuid;
	private String name;
	private Warning[] warnings;
	private Report[] reports;
	
	public User(){}
	
	public User(String uuid, String name, Report[] reports, Warning[] warnings)
	{
		this.uuid = uuid;
		this.name = name;
		this.reports = reports;
		this.warnings = warnings;
	}
	
	public User(String uuid, String name, Report[] reports)
	{
		this(uuid, name, reports, null);
	}
	
	public User(String uuid, String name, Warning[] warnings)
	{
		this(uuid, name, null, warnings);
	}
	
	public User(String uuid, String name)
	{
		this(uuid, name, null, null);
	}
	
	public Set<User> getUsers()
	{
		return users;
	}
	
	public void addUser(User user)
	{
		users.add(user);
	}
	
	public void removeUser(User user)
	{
		users.remove(user);
	}
	
	public User getUser(Player player)
	{
		for(User user : users)
		{
			if(user.getName().equals(player.getName()))
			{
				return user;
			}
		}
		
		return new User(player.getUniqueId().toString(), player.getName());
	}
	
	public String getUuid()
	{
		return uuid;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Report[] reports()
	{
		return reports;
	}
	
	public Warning[] warnings()
	{
		return warnings;
	}
	
	public int getReports()
	{
		if(reports == null)
		{
			return 0;
		}
		
		return reports.length;
	}
	
	public int getWarnings()
	{
		if(warnings == null)
		{
			return 0;
		}
		
		return warnings.length;
	}
	
	public void addReport(Report report)
	{
		List<Report> newReports = new ArrayList<Report>();
		Report[] newArray = new Report[getReports() + 1];
		
		if(reports != null)
		{
			for(Report r : reports)
			{
				newReports.add(r);
			}
		}
		
		newReports.add(report);
		
		reports = newReports.toArray(newArray);
	}
	
	public void addWarning(Warning warning)
	{
		List<Warning> newWarnings = new ArrayList<Warning>();
		Warning[] newArray = new Warning[getWarnings() + 1];
		
		if(warnings != null)
		{
			for(Warning w : warnings)
			{
				newWarnings.add(w);
			}
		}
		
		newWarnings.add(warning);
		
		warnings = newWarnings.toArray(newArray);
	}
	
	public void clearReports()
	{
		this.reports = null;
	}
	
	public void clearWarnings()
	{
		this.warnings = null;
	}
}
