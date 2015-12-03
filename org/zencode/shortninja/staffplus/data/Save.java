package org.zencode.shortninja.staffplus.data;

import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.types.Report;
import org.zencode.shortninja.staffplus.types.User;
import org.zencode.shortninja.staffplus.types.Warning;

public class Save
{
	private User user;
	private String name;
	private String uuid;

	public Save(User user)
	{
		this.user = user;
		this.name = user.getName();
		this.uuid = user.getUuid();
		
		save();
	}
	
	private void save()
	{
		StaffPlus.get().data.getData().set(uuid + ".name", name);
		
		if(user.getReports() > 0)
		{
			for(int i = 0; i < user.reports().length; i++)
			{
				Report report = user.reports()[i];
				
				StaffPlus.get().data.getData().set(uuid + ".reports." + i, report.getReason());
			}
		}else if(StaffPlus.get().data.getData().contains(uuid + ".reports"))
		{
			StaffPlus.get().data.getData().set(uuid + ".reports", null);
		}
		
		if(user.getWarnings() > 0)
		{
			for(int i = 0; i < user.warnings().length; i++)
			{
				Warning warning = user.warnings()[i];
				
				StaffPlus.get().data.getData().set(uuid + ".warnings." + i, warning.getReason());
			}
		}else if(StaffPlus.get().data.getData().contains(uuid + ".warnings"))
		{
			StaffPlus.get().data.getData().set(uuid + ".warnings", null);
		}
		
		StaffPlus.get().data.saveData();
	}
}
