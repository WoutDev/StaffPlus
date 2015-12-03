package org.zencode.shortninja.staffplus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.zencode.shortninja.staffplus.commands.bases.AlertsCmd;
import org.zencode.shortninja.staffplus.commands.bases.CPSCmd;
import org.zencode.shortninja.staffplus.commands.bases.ChatCmd;
import org.zencode.shortninja.staffplus.commands.bases.ExamineCmd;
import org.zencode.shortninja.staffplus.commands.bases.FreezeCmd;
import org.zencode.shortninja.staffplus.commands.bases.ReportCmd;
import org.zencode.shortninja.staffplus.commands.bases.SCCmd;
import org.zencode.shortninja.staffplus.commands.bases.StaffCmd;
import org.zencode.shortninja.staffplus.commands.bases.VanishCmd;
import org.zencode.shortninja.staffplus.commands.bases.WarnCmd;

public class Hub
{
	public Hub(CommandSender sender, Command cmd, String string, String[] args)
	{
		Executor exe = null;
		
		if(cmd.getName().equalsIgnoreCase("staff"))
		{
			exe = new StaffCmd();
		}else if(cmd.getName().equalsIgnoreCase("freeze"))
		{
			exe = new FreezeCmd();
		}else if(cmd.getName().equalsIgnoreCase("examine"))
		{
			exe = new ExamineCmd();
		}else if(cmd.getName().equalsIgnoreCase("cps"))
		{
			exe = new CPSCmd();
		}else if(cmd.getName().equalsIgnoreCase("sc"))
		{
			exe = new SCCmd();
		}else if(cmd.getName().equalsIgnoreCase("report"))
		{
			exe = new ReportCmd();
		}else if(cmd.getName().equalsIgnoreCase("warn"))
		{
			exe = new WarnCmd();
		}else if(cmd.getName().equalsIgnoreCase("v"))
		{
			exe = new VanishCmd();
		}else if(cmd.getName().equalsIgnoreCase("chat"))
		{
			exe = new ChatCmd();
		}else if(cmd.getName().equalsIgnoreCase("alerts"))
		{
			exe = new AlertsCmd();
		}
		
		if(exe != null)
		{
			exe.execute(sender, string, args);
		}
	}
}
