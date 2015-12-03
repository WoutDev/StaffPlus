package org.zencode.shortninja.staffplus;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.zencode.shortninja.staffplus.commands.Hub;
import org.zencode.shortninja.staffplus.data.Changelog;
import org.zencode.shortninja.staffplus.data.DataFile;
import org.zencode.shortninja.staffplus.data.Management;
import org.zencode.shortninja.staffplus.data.Storage;
import org.zencode.shortninja.staffplus.gadgets.CPS;
import org.zencode.shortninja.staffplus.gadgets.Follow;
import org.zencode.shortninja.staffplus.gadgets.Freeze;
import org.zencode.shortninja.staffplus.gadgets.Launch;
import org.zencode.shortninja.staffplus.gadgets.Teleport;
import org.zencode.shortninja.staffplus.guis.WarningGUI;
import org.zencode.shortninja.staffplus.listeners.AsyncPlayerChat;
import org.zencode.shortninja.staffplus.listeners.BlockBreak;
import org.zencode.shortninja.staffplus.listeners.EntityDamage;
import org.zencode.shortninja.staffplus.listeners.EntityDamageByEntity;
import org.zencode.shortninja.staffplus.listeners.InventoryClick;
import org.zencode.shortninja.staffplus.listeners.PlayerCommandPreprocess;
import org.zencode.shortninja.staffplus.listeners.PlayerDeath;
import org.zencode.shortninja.staffplus.listeners.PlayerDropItem;
import org.zencode.shortninja.staffplus.listeners.PlayerInteract;
import org.zencode.shortninja.staffplus.listeners.PlayerInteractEntity;
import org.zencode.shortninja.staffplus.listeners.PlayerJoin;
import org.zencode.shortninja.staffplus.listeners.PlayerMove;
import org.zencode.shortninja.staffplus.listeners.PlayerQuit;
import org.zencode.shortninja.staffplus.methods.Alert;
import org.zencode.shortninja.staffplus.methods.Chat;
import org.zencode.shortninja.staffplus.methods.Lockdown;
import org.zencode.shortninja.staffplus.methods.Mode;
import org.zencode.shortninja.staffplus.methods.Staff;
import org.zencode.shortninja.staffplus.methods.Vanish;
import org.zencode.shortninja.staffplus.packets.PacketListener;
import org.zencode.shortninja.staffplus.types.Report;
import org.zencode.shortninja.staffplus.types.User;
import org.zencode.shortninja.staffplus.util.shortninja.Inventory;
import org.zencode.shortninja.staffplus.util.shortninja.Message;
import org.zencode.shortninja.staffplus.util.shortninja.Permission;
import org.zencode.shortninja.staffplus.util.shortninja.Sound;

public class StaffPlus extends JavaPlugin 
{
    private static StaffPlus plugin;
    private PacketListener packetListener = new PacketListener();
    
    public FileConfiguration config = getConfig();
    public Management management = new Management();
    public DataFile data = DataFile.getInstance();
    public Alert alert = new Alert();
    public Chat chat = new Chat();
    public Lockdown lockdown = new Lockdown();
    public Mode mode = new Mode();
    public Staff staff = new Staff();
    public Vanish vanish = new Vanish();
    public Follow follow = new Follow();
    public Freeze freeze = new Freeze();
    public Launch launch = new Launch();
    public Teleport teleport = new Teleport();
    public Inventory inventory = new Inventory();
    public Message message = new Message();
    public Permission permission = new Permission();
    public Sound sound = new Sound();
    public CPS cps = new CPS();
    public Report report = new Report();
    public User user = new User();
    public WarningGUI warningGUI = new WarningGUI();
    public Storage storage;
    
	@Override
	public void onEnable() 
	{
		ConsoleCommandSender console = getServer().getConsoleSender();
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		
		console.sendMessage(message.colorize("&2[Staff+] &aStaff+ has been enabled!"));
		console.sendMessage(message.colorize("&2[Staff+] &aPlugin by Shortninja."));
		
		plugin = this;
		
		data.setup(plugin);
		this.saveDefaultConfig();
		
		storage = new Storage();
		
		start(console, pluginManager);
	}
	
	@Override
	public void onDisable() 
	{
		ConsoleCommandSender console = getServer().getConsoleSender();
		console.sendMessage(message.colorize("&4[Staff+] &cStaff+ has been disabled!"));
		
		stop();
		
		plugin = null;
	}
	
	public static StaffPlus get()
	{
		return plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		new Hub(sender, cmd, label, args);
		return true;
	}
	
	private void start(ConsoleCommandSender console, PluginManager pluginManager)
	{
		management.openServer();
		
		new Changelog();
		
		checkProtocolLib(console, pluginManager);
		registerListeners();
		
		packetListener.chestAnimationListener();
		packetListener.chestSoundListener();
	}
	
	private void stop()
	{
		management.closeServer();
		
		for(String offlinePlayer : Mode.active)
		{
			Player onlinePlayer = Bukkit.getPlayer(offlinePlayer);
			
			if(onlinePlayer != null)
			{
				mode.disable(onlinePlayer);
			}
		}
	}
	
	private void checkProtocolLib(ConsoleCommandSender console, PluginManager pluginManager)
	{
		if(pluginManager.getPlugin("ProtocolLib") == null)
		{
			console.sendMessage(message.colorize("&4[Staff+] &cStaff+ requires ProtocolLib to run!"));
			Bukkit.getServer().getPluginManager().disablePlugin(plugin);
		}
	}
	
	private void registerListeners()
	{
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		
        pluginManager.registerEvents(new AsyncPlayerChat(), this);
        pluginManager.registerEvents(new BlockBreak(), this);
        pluginManager.registerEvents(new EntityDamage(), this);
        pluginManager.registerEvents(new EntityDamageByEntity(), this);
        pluginManager.registerEvents(new InventoryClick(), this);
        pluginManager.registerEvents(new PlayerCommandPreprocess(), this);
        pluginManager.registerEvents(new PlayerDeath(), this);
        pluginManager.registerEvents(new PlayerDropItem(), this);
        pluginManager.registerEvents(new PlayerInteract(), this);
        pluginManager.registerEvents(new PlayerInteractEntity(), this);
        pluginManager.registerEvents(new PlayerJoin(), this);
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new PlayerQuit(), this);
        pluginManager.registerEvents(new PacketListener(), this);
	}
}