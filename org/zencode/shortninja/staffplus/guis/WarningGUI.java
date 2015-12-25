package org.zencode.shortninja.staffplus.guis;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.zencode.shortninja.staffplus.StaffPlus;

public class WarningGUI
{
	private static Map<String, WarningGUI> waitingForReason = new HashMap<String, WarningGUI>();
	private Player player;
	private Player targetPlayer;
	private String uuid;
	private String name;
	
	public WarningGUI(){};
	
	public WarningGUI(Player player, Player targetPlayer)
	{
		this.player = player;
		this.targetPlayer = targetPlayer;
		this.uuid = targetPlayer.getUniqueId().toString();
		this.name = targetPlayer.getName();
		
		notifyReason();
	}
	
	public boolean isWaitingForReason(String playerName)
	{
		return waitingForReason.containsKey(playerName);
	}
	
	public WarningGUI getWaitingForReason(String playerName)
	{
		return waitingForReason.get(playerName);
	}
	
	public void removeWaitingForReason(String playerName)
	{
		waitingForReason.remove(playerName);
	}
	
	public Player getPlayer()
	{
		return targetPlayer;
	}
	
	public String getUuid()
	{
		return uuid;
	}
	
	public String getName()
	{
		return name;
	}
	
	private void notifyReason()
	{
		player.sendMessage(" ");
		player.sendMessage(StaffPlus.get().message.warningsMessage("&bType a reason in chat."));
		player.sendMessage(" ");
		waitingForReason.put(player.getName(), this);
	}
}
