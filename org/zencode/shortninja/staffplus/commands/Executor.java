package org.zencode.shortninja.staffplus.commands;

import org.bukkit.command.CommandSender;

public abstract interface Executor
{
	public abstract void execute(
			CommandSender paramCommandSender, 
				String paramString, 
				String[] paramArrayOfString);
}
