package org.zencode.shortninja.staffplus.gadgets;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.zencode.shortninja.staffplus.StaffPlus;

public class CPS
{
	private static Map<String, Integer> currentlyTesting = new HashMap<String, Integer>();
	private Player player;
	private Player targetPlayer;
	
	public CPS(){}
	
	public CPS(Player player, Player targetPlayer)
	{
		this.player = player;
		this.targetPlayer = targetPlayer;
		
		start();
		timer();
	}
	
	public int getClicks(String playerName)
	{
		return currentlyTesting.get(playerName);
	}
	
	public boolean isTesting(String playerName)
	{
		return currentlyTesting.containsKey(playerName);
	}
	
	public void updateClicks(String playerName, int clickCount)
	{
		currentlyTesting.put(playerName, clickCount);
	}
	
	public void removeTesting(String playerName)
	{
		currentlyTesting.remove(playerName);
	}
	
	public void start()
	{
		currentlyTesting.put(targetPlayer.getName(), 0);
		player.sendMessage(StaffPlus.get().message.generalMessage("&bTesting clicks for &7" + targetPlayer.getName() + "&b..."));
	}
	
	private void timer()
	{
		new BukkitRunnable()
		{
			public void run()
			{
				showClicks();
				
				currentlyTesting.remove(targetPlayer.getName());
				
				this.cancel();
			}
		}.runTaskLater(StaffPlus.get(), StaffPlus.get().storage.cpsTime * 20);
	}
	
	private void showClicks()
	{
		int clicks = currentlyTesting.get(targetPlayer.getName());
		float calculate = (float) clicks / (float) StaffPlus.get().storage.cpsTime;
        DecimalFormat cps = new DecimalFormat("#.##");
		
		player.sendMessage(StaffPlus.get().message.generalMessage("&7" + targetPlayer.getName()
		        + " &bleft clicked &7" + clicks + " &btimes in &7"
		        + StaffPlus.get().storage.cpsTime + " seconds&b &7(" + cps.format(calculate) + " CPS)&b."));
	}
}
