package org.zencode.shortninja.staffplus.listeners;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.zencode.shortninja.staffplus.StaffPlus;
import org.zencode.shortninja.staffplus.methods.Mode;

public class BlockBreak implements Listener
{
	private static Set<Location> ignoredBlocks = new HashSet<Location>();
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onBreak(BlockBreakEvent event)
	{
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		if(StaffPlus.get().storage.modeBlockManipulationDisabled && Mode.active.contains(player.getName()))
		{
			event.setCancelled(true);
			return;
		}
		
		if(!StaffPlus.get().storage.alertsXrayEnabled)
		{
			return;
		}
		
		if(isXrayBlock(block.getType()))
		{
			StaffPlus.get().alert.notifyXray(player, block.getType().toString(), 1);
		}
	}
	
	private boolean isXrayBlock(Material minedBlock)
	{
		boolean isXray = false;
		
		for(String string : StaffPlus.get().storage.alertsXrayBlocksList)
		{
			if(!StaffPlus.get().inventory.isActualBlock(string))
			{
				continue;
			}
			
			Material xrayBlock = Material.valueOf(string);
			
			if(minedBlock == xrayBlock && xrayBlock != null)
			{
				isXray = true;
				break;
			}
		}
		
		return isXray;
	}
	
	/*
	 * This method will be used later when I decide to
	 * get around to making the ore mine detection. :P
	*/
	private int getSurroundingBlocks(Block block)
	{
		int count = 1;
		
		for(BlockFace face : blockFaces)
		{
			if((block.getRelative(face).getType() == block.getType()) && !(ignoredBlocks.contains(block.getRelative(face).getLocation())))
			{
				count++;
				ignoredBlocks.add(block.getRelative(face).getLocation());
			}
		}
		
		return count;
	}
	
	private BlockFace[] blockFaces = {BlockFace.DOWN, BlockFace.EAST, 
	        BlockFace.EAST_SOUTH_EAST, BlockFace.NORTH, BlockFace.NORTH_EAST,
	        BlockFace.NORTH_NORTH_EAST, BlockFace.NORTH_NORTH_WEST,
	        BlockFace.NORTH_WEST, BlockFace.SOUTH, BlockFace.SOUTH_EAST,
	        BlockFace.SOUTH_SOUTH_EAST, BlockFace.SOUTH_SOUTH_WEST,
	        BlockFace.SOUTH_WEST, BlockFace.UP, BlockFace.WEST,
	        BlockFace.WEST_NORTH_WEST, BlockFace.WEST_SOUTH_WEST,
	        BlockFace.EAST_NORTH_EAST};
}
