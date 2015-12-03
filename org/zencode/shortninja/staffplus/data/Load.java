package org.zencode.shortninja.staffplus.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.types.Report;
import org.zencode.shortninja.staffplus.types.User;
import org.zencode.shortninja.staffplus.types.Warning;

public class Load
{
	private String name;
	private String uuid;
	private boolean isNew;
	
	public Load(Player player, boolean isNew)
	{
		this.name = player.getName();
		this.uuid = player.getUniqueId().toString();
		this.isNew = isNew;
		
		try
        {
	        load();
        }catch(SQLException exception)
        {
        	exception.printStackTrace();
        }
	}
	
	private void load() throws SQLException
	{
		String storedName = StaffPlus.get().data.getData().getString(uuid + ".name");
		Report[] reportsArray = null;
		Warning[] warningsArray = null;
		User user = null;
		
		if(!name.equals(storedName) && !isNew)
		{
			StaffPlus.get().alert.notifyNameChange(storedName, name);
		}
		
		if(StaffPlus.get().data.getData().contains(uuid + ".reports"))
		{
			List<String> reports = new ArrayList<String>();
			for(String string : StaffPlus.get().data.getData().getConfigurationSection(uuid + ".reports").getKeys(false))
			{
				String reason = StaffPlus.get().data.getData().getString(uuid + ".reports." + string);
				reports.add(reason);
			}
			
			reportsArray = reportArray(reports);
		}
		
		if(StaffPlus.get().data.getData().contains(uuid + ".warnings"))
		{
			List<String> warnings = new ArrayList<String>();
			for(String string : StaffPlus.get().data.getData().getConfigurationSection(uuid + ".warnings").getKeys(false))
			{
				String reason = StaffPlus.get().data.getData().getString(uuid + ".warnings." + string);
				warnings.add(reason);
			}
			
			warningsArray = warningArray(warnings);
		}
		
		if(reportsArray != null && warningsArray != null)
		{
			user = new User(uuid, name, reportsArray, warningsArray);
		}else if(reportsArray != null && warningsArray == null)
		{
			user = new User(uuid, name, reportsArray);
		}else if(reportsArray == null && warningsArray != null)
		{
			user = new User(uuid, name, warningsArray);
		}else
		{
			user = new User(uuid, name);
		}
		
		StaffPlus.get().user.addUser(user);
	}
	
	private Report[] reportArray(List<String> reports)
	{
		List<Report> report = new ArrayList<Report>();
		Report[] reportArray = new Report[reports.size()];
		
		for(String reason : reports)
		{
			report.add(new Report(uuid, name, reason, false));
		}
		
		return report.toArray(reportArray);
	}
	
	private Warning[] warningArray(List<String> warnings)
	{
		List<Warning> warning = new ArrayList<Warning>();
		Warning[] warningArray = new Warning[warnings.size()];
		
		for(String reason : warnings)
		{
			warning.add(new Warning(uuid, name, reason, false));
		}
		
		return warning.toArray(warningArray);
	}
}
