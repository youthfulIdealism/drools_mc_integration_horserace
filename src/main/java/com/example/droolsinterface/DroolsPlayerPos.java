package com.example.droolsinterface;

import net.minecraft.util.BlockPos;

/**
 * Stub example class that should probably get removed. This is only here so that
 * we have a working example.
 * @author Samuel
 *
 */
public class DroolsPlayerPos
{
	public int x;
	public int y;
	public int z;
	
	public DroolsPlayer player;
	
	/*public DroolsPlayerPos(DroolsPlayer player, int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
	}*/
	
	public DroolsPlayerPos(DroolsPlayer player)
	{
		BlockPos pos = player.base.getPosition();
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		this.player = player;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}

	public DroolsPlayer getPlayer() {
		return player;
	}

	public void setPlayer(DroolsPlayer player) {
		this.player = player;
	}
	
	

}
