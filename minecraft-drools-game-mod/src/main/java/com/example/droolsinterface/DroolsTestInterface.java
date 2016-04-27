package com.example.droolsinterface;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

/**
 * Stub. None of these work. Don't use them.
 * @author Samuel
 *
 */
public class DroolsTestInterface
{
	public static boolean withinPos(Entity test, int x, int y, int z, int tx, int ty, int tz)
	{
		boolean returnable = true;
		BlockPos entityPos = test.getPosition();
		returnable = returnable && within(entityPos.getX(), x, tx);
		returnable = returnable && within(entityPos.getY(), y, ty);
		returnable = returnable && within(entityPos.getZ(), z, tz);
		return returnable;
	}
	
	public static boolean within(int test, int first, int second)
	{
		if(first < second)
		{
			return test >= first && test <= second;
		}else
		{
			return test >= second && test <= first;
		}
	}
}
