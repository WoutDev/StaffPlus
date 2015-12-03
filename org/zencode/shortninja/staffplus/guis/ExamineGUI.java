package org.zencode.shortninja.staffplus.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.types.User;
import org.zencode.shortninja.staffplus.util.ArrayUtils;
import org.zencode.shortninja.staffplus.util.hex.Items;

public class ExamineGUI
{
	private Player targetPlayer;
	
	public ExamineGUI(Player player, Player targetPlayer)
	{
		this.targetPlayer = targetPlayer;

		player.openInventory(extraInventory(inventory()));
	}
	
	private Inventory extraInventory(Inventory inventory)
	{
		ItemStack glass = new ItemStack(Material.STAINED_GLASS, 1, (short) 14);
		ItemStack leftSkull = new ItemStack(Material.STAINED_GLASS);
		ItemStack rightSkull = new ItemStack(Material.STAINED_GLASS, 1);
		
		inventory.setItem(45, leftSkull);
		
		if(StaffPlus.get().storage.inventoryExtraInfo)
		{
			inventory.setItem(46, foodItem());
			inventory.setItem(47, compassItem());
			inventory.setItem(48, grassItem());
			inventory.setItem(49, mapItem());
			inventory.setItem(50, bookItem());
		}else
		{
			inventory.setItem(46, glass);
			inventory.setItem(47, glass);
			inventory.setItem(48, glass);
			inventory.setItem(49, glass);
			inventory.setItem(50, glass);
		}
		
		if(StaffPlus.get().storage.inventoryOptions)
		{
			inventory.setItem(51, paperItem());
			inventory.setItem(52, blazeItem());
		}else
		{
			inventory.setItem(51, glass);
			inventory.setItem(52, glass);
		}
		
		inventory.setItem(53, rightSkull);
		
		return inventory;
	}
	
	private Inventory inventory()
	{
		Inventory inventory = Bukkit.createInventory(null, 54, StaffPlus.get().message.colorize("&c&lExamine " + targetPlayer.getName()));
		ItemStack[] contents = targetPlayer.getInventory().getContents();
		ItemStack[] armor = targetPlayer.getInventory().getArmorContents();
		ArrayUtils.reverse(armor);
		ItemStack leftSkull = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemStack rightSkull = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		
		for(int i = 0; i < contents.length - 1; i++)
		{
			ItemStack item = contents[i];
			
			inventory.setItem(i, item);
		}
		
		inventory.setItem(37, leftSkull);
		
		for(int i = 0; i <= armor.length - 1; i++)
		{
			ItemStack item = armor[i];
			
			if(i == 3)
			{
				inventory.setItem(39 + i, targetPlayer.getItemInHand());
			}
			
			inventory.setItem(38 + i, item);
		}
		
		inventory.setItem(43, rightSkull);
		
		return inventory;
	}
	
	private ItemStack foodItem()
	{
		int healthLevel = (int) targetPlayer.getHealth();
		int foodLevel = (int) targetPlayer.getFoodLevel();
		
		ItemStack item = Items.builder()
				.setMaterial(Material.BREAD).setAmount(1)
				.setName(StaffPlus.get().message.colorize("&4Food"))
				.addLore("&bHealth: &7" + healthLevel + "/20", "&bHunger: &7" + foodLevel + "/20")
				.build();
		
		return item;
	}
	
	private ItemStack grassItem()
	{
		String gamemode = targetPlayer.getGameMode().toString();
		
		ItemStack item = Items.builder()
				.setMaterial(Material.GRASS).setAmount(1)
				.setName(StaffPlus.get().message.colorize("&4Gamemode"))
				.addLore("&bGamemode: &7" + gamemode)
				.build();
		
		return item;
	}
	
	private ItemStack mapItem()
	{
		String world = targetPlayer.getWorld().getName();
		String coordinates = targetPlayer.getLocation().getBlockX() + "," + targetPlayer.getLocation().getBlockY() + "," + targetPlayer.getLocation().getBlockZ();
		
		ItemStack item = Items.builder()
				.setMaterial(Material.MAP).setAmount(1)
				.setName(StaffPlus.get().message.colorize("&4Location"))
				.addLore("&4World: &7" + world, "&4Coordinates: &7" + coordinates)
				.build();
		
		return item;
	}
	
	private ItemStack compassItem()
	{
		String ip = targetPlayer.getAddress().getAddress().getHostAddress().replace("/", "");
		
		ItemStack item = Items.builder()
				.setMaterial(Material.COMPASS).setAmount(1)
				.setName(StaffPlus.get().message.colorize("&4IP"))
				.addLore("&bIP Address: &7" + ip)
				.build();
		
		return item;
	}
	
	private ItemStack bookItem()
	{
		User user = StaffPlus.get().user.getUser(targetPlayer);
		int warnings = user.getWarnings();
		int reports = user.getReports();
		
		ItemStack item = Items.builder()
				.setMaterial(Material.BOOK).setAmount(1)
				.setName(StaffPlus.get().message.colorize("&4Infractions"))
				.addLore("&bWarnings: &7" + warnings, "&bReports: &7" + reports)
				.build();
		
		return item;
	}
	
	private ItemStack paperItem()
	{
		ItemStack item = Items.builder()
				.setMaterial(Material.PAPER).setAmount(1)
				.setName(StaffPlus.get().message.colorize("&bWarn Player"))
				.addLore("&7Click to warn this player.")
				.build();
		
		return item;
	}
	
	private ItemStack blazeItem()
	{
		ItemStack item = Items.builder()
				.setMaterial(Material.BLAZE_ROD).setAmount(1)
				.setName(StaffPlus.get().message.colorize("&bFreeze Player"))
				.addLore("&7Click to toggle freeze on this player.")
				.build();
		
		return item;
	}
}
