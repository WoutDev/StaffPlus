package org.zencode.shortninja.staffplus.commands;

import org.bukkit.command.CommandSender;

public abstract interface Executors
{
	public abstract void execute(
			CommandSender paramCommandSender, 
				String paramString, 
				String[] paramArrayOfString);
}
