package org.zencode.shortninja.staffplus.data;
 
import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.zencode.shortninja.staffplus.StaffPlus;
 
public class DataFile
{
	private static DataFile instance = new DataFile();
	private final String FILE_NAME = "data.yml";
	private FileConfiguration data;
	private File dataFile;
	private ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	
	public static DataFile getInstance()
	{
		return instance;
	}
	
	public void setup(Plugin plugin)
	{
		dataFile = new File(plugin.getDataFolder(), FILE_NAME);
		
		if(!dataFile.exists())
		{
			try
			{
				dataFile.createNewFile();
			}catch(IOException exception)
			{
				console.sendMessage(StaffPlus.get().message.colorize("&4[StaffPlus] &cError occured with initializing '" + FILE_NAME + "'!"));
			}
		}
		
		data = YamlConfiguration.loadConfiguration(dataFile);
	}
	
	public FileConfiguration getData()
	{
		return data;
	}
	
	public void saveData()
	{
		try
		{
			data.save(dataFile);
		}catch(IOException e)
		{
			console.sendMessage(StaffPlus.get().message.colorize("&4[BattlePets] &cError occured with saving '" + FILE_NAME + "'!"));
		}
	}
	
	public void reloadData()
	{
		data = YamlConfiguration.loadConfiguration(dataFile);
	}
}