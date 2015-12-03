package org.zencode.shortninja.staffplus.packets;

import java.lang.reflect.Constructor;

import org.bukkit.util.Vector;

import com.comphenix.protocol.reflect.EquivalentConverter;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.google.common.base.Objects;

public class BlockPosition
{
	public static BlockPosition ORIGIN = new BlockPosition(0, 0, 0);
	
	private static Constructor<?> blockPositionConstructor;
	
	protected final int x;
	protected final int y;
	protected final int z;
	
	private static StructureModifier<Integer> intModifier;
	
	public BlockPosition(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public BlockPosition(Vector vector)
	{
		if(vector == null)
		    throw new IllegalArgumentException("Vector cannot be NULL.");
		this.x = vector.getBlockX();
		this.y = vector.getBlockY();
		this.z = vector.getBlockZ();
	}
	
	public Vector toVector()
	{
		return new Vector(x, y, z);
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getZ()
	{
		return z;
	}
	
	public BlockPosition add(BlockPosition other)
	{
		if(other == null)
		    throw new IllegalArgumentException("other cannot be NULL");
		return new BlockPosition(x + other.x, y + other.y, z + other.z);
	}
	
	public BlockPosition subtract(BlockPosition other)
	{
		if(other == null)
		    throw new IllegalArgumentException("other cannot be NULL");
		return new BlockPosition(x - other.x, y - other.y, z - other.z);
	}
	
	public BlockPosition multiply(int factor)
	{
		return new BlockPosition(x * factor, y * factor, z * factor);
	}
	
	public BlockPosition divide(int divisor)
	{
		if(divisor == 0)
		    throw new IllegalArgumentException("Cannot divide by null.");
		return new BlockPosition(x / divisor, y / divisor, z / divisor);
	}
	
	public static EquivalentConverter<BlockPosition> getConverter()
	{
		return new EquivalentConverter<BlockPosition>()
		{
			@Override
			public Object getGeneric(Class<?> genericType,
			        BlockPosition specific)
			{
				if(blockPositionConstructor == null)
				{
					try
					{
						blockPositionConstructor = MinecraftReflection
						        .getBlockClass().getConstructor(int.class,
						                int.class, int.class);
					} catch(Exception e)
					{
						throw new RuntimeException(
						        "Cannot find block position constructor.", e);
					}
				}
				
				try
				{
					Object result = blockPositionConstructor.newInstance(
					        specific.x, specific.y, specific.z);
					return result;
				} catch(Exception e)
				{
					throw new RuntimeException(
					        "Cannot construct BlockPosition.", e);
				}
			}
			
			@Override
			public BlockPosition getSpecific(Object generic)
			{
				if(MinecraftReflection.isChunkPosition(generic))
				{
					intModifier = new StructureModifier<Object>(
					        generic.getClass(), null, false)
					        .withType(int.class);
					
					if(intModifier.size() < 3)
					{
						throw new IllegalStateException("Cannot read class "
						        + generic.getClass()
						        + " for its integer fields.");
					}
					
					if(intModifier.size() >= 3)
					{
						try
						{
							StructureModifier<Integer> instance = intModifier
							        .withTarget(generic);
							BlockPosition result = new BlockPosition(
							        instance.read(0), instance.read(1),
							        instance.read(2));
							return result;
						} catch(FieldAccessException e)
						{
							throw new RuntimeException("Field access error.", e);
						}
					}
				}
				
				return null;
			}
			
			@Override
			public Class<BlockPosition> getSpecificType()
			{
				return BlockPosition.class;
			}
		};
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj) return true;
		if(obj == null) return false;
		
		if(obj instanceof BlockPosition)
		{
			BlockPosition other = (BlockPosition) obj;
			return x == other.x && y == other.y && z == other.z;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hashCode(x, y, z);
	}
	
	@Override
	public String toString()
	{
		return "BlockPosition [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}