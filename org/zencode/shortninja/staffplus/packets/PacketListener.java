package org.zencode.shortninja.staffplus.packets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.zencode.shortninja.staffplus.StaffPlus;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

public class PacketListener implements Listener
{
	public void chestAnimationListener()
	{
		ProtocolLibrary.getProtocolManager().addPacketListener(
		        new PacketAdapter(StaffPlus.get(), ListenerPriority.HIGH,
		                PacketType.Play.Server.BLOCK_ACTION)
		        {
			        @Override
			        public void onPacketSending(PacketEvent event)
			        {
				        if(event.getPacketType() == PacketType.Play.Server.BLOCK_ACTION)
				        {
					        Player player = event.getPlayer();
					        if(StaffPlus.get().mode.isActive(player.getName()))
					        {
						        event.setCancelled(true);
					        }
				        }
			        }
		        });
	}
	
	public void chestSoundListener()
	{
		ProtocolLibrary.getProtocolManager().addPacketListener(
		        new PacketAdapter(StaffPlus.get(), ListenerPriority.HIGH,
		                PacketType.Play.Server.NAMED_SOUND_EFFECT)
		        {
			        @Override
			        public void onPacketSending(PacketEvent event)
			        {
				        if(event.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT)
				        {
					        Player listener = event.getPlayer();
					        if(!(event.getPacket().getStrings().read(0)
					                .equalsIgnoreCase("random.chestopen") || event
					                .getPacket().getStrings().read(0)
					                .equalsIgnoreCase("random.chestclosed")))
					        {
						        return;
					        }
					        
					        Location loc = new Location(
					                listener.getWorld(),
					                event.getPacket().getIntegers().read(0) / 8,
					                event.getPacket().getIntegers().read(1) / 8,
					                event.getPacket().getIntegers().read(2) / 8);
					        Block b = listener.getWorld().getBlockAt(loc);
					        check: if(!(b.getState() instanceof Chest))
					        {
						        List<Location> adjacentBlockLocations = getAdjacentBlockLocations(loc);
						        for(Location otherLocation : adjacentBlockLocations)
						        {
							        Block otherBlock = listener.getWorld()
							                .getBlockAt(otherLocation);
							        if(otherBlock.getState() instanceof Chest)
							        {
								        b = otherBlock;
								        loc = otherLocation;
								        break check;
							        }
						        }
						        return;
					        }
					        
					        if(event.getPacket().getStrings().read(0)
					                .equalsIgnoreCase("random.chestclosed"))
					        {
						        for(Player p : listener.getWorld().getPlayers())
						        {
							        if(StaffPlus.get().mode.isActive(p.getName()))
							        {
								        event.setCancelled(true);
							        }
						        }
						        return;
					        }
					        
					        Chest chest = (Chest) b.getState();
					        Inventory inv = chest.getBlockInventory();
					        List<HumanEntity> humanViewers = inv.getViewers();
					        for(HumanEntity entity : humanViewers)
					        {
						        if(entity instanceof Player)
						        {
							        Player player = (Player) entity;
							        if(StaffPlus.get().mode.isActive(player.getName()))
							        {
								        event.setCancelled(true);
							        }
						        }
					        }
				        }
			        }
		        });
	}
	
	private Location add(Location l, int x, int z)
	{
		return new Location(l.getWorld(), l.getX() + x, l.getY(), l.getZ() + z);
	}
	
	private List<Location> getAdjacentBlockLocations(Location loc)
	{
		List<Location> adjacentBlockLocations = new ArrayList<Location>();
		
		adjacentBlockLocations.add(add(loc, 1, 0));
		adjacentBlockLocations.add(add(loc, -1, 0));
		adjacentBlockLocations.add(add(loc, 0, -1));
		adjacentBlockLocations.add(add(loc, 0, 1));
		adjacentBlockLocations.add(add(loc, 1, 1));
		adjacentBlockLocations.add(add(loc, -1, -1));
		adjacentBlockLocations.add(add(loc, 1, -1));
		adjacentBlockLocations.add(add(loc, -1, 1));
		
		return adjacentBlockLocations;
	}
}