package org.zencode.shortninja.staffplus.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.types.Report;
import org.zencode.shortninja.staffplus.types.User;

public class ReportGUI
{
	public ReportGUI(Player player)
	{
		player.openInventory(inventory());
	}
	
	private Inventory inventory()
	{
		Inventory inventory = Bukkit.createInventory(null, 54, StaffPlus.get().message.colorize("&c&lUnresolved Reports"));
		int count = 0;
		
		for(String name : StaffPlus.get().report.getUnresolvedReports().keySet())
		{
			if(count >= 53)
			{
				break;
			}
			
			Player player = Bukkit.getPlayer(name);
			Report report = StaffPlus.get().report.getUnresolvedReports().get(name);
			
			if(player == null)
			{
				continue;
			}
			
			inventory.setItem(count, createItem(player, report));
			count++;
		}
		
		return inventory;
	}
	
	private ItemStack createItem(Player player,  Report report)
	{
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
		List<String> headLore = new ArrayList<String>();
		User user = StaffPlus.get().user.getUser(player);
		int warnings = user.getWarnings();
		int reports = user.getReports();
		
		skullMeta.setDisplayName(StaffPlus.get().message.colorize("&b" + report.getName()));
		skullMeta.setOwner(report.getName());
		
		headLore.add(StaffPlus.get().message.colorize("&bWarnings: &7" + warnings));
		headLore.add(StaffPlus.get().message.colorize("&bReports: &7" + reports));
		headLore.add(StaffPlus.get().message.colorize("&bReport Reason: &7" + report.getReason()));
		
		skullMeta.setLore(headLore);
		head.setItemMeta(skullMeta);
		
		return head;
	}
}
