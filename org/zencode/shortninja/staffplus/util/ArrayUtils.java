package org.zencode.shortninja.staffplus.util;

public class ArrayUtils
{
	public static void reverse(Object array[], int startIndexInclusive,
	        int endIndexExclusive)
	{
		if(array == null) return;
		int i = startIndexInclusive >= 0 ? startIndexInclusive : 0;
		for(int j = Math.min(array.length, endIndexExclusive) - 1; j > i; i++)
		{
			Object tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
		}
	}
	
	public static void reverse(Object array[])
	{
		if(array == null)
		{
			return;
		} else
		{
			reverse(array, 0, array.length);
			return;
		}
	}
}
