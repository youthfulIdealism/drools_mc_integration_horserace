package com.example.droolsinterface;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Stub class used to wrap the player.
 * 
 * //TODO: research viability of replacing EntityPlayer with EntityPlayerMP
 * @author Samuel
 *
 */
public class DroolsPlayer
{
	public EntityPlayer base;
	public DroolsPlayerPos position;
	
	public DroolsPlayer(EntityPlayer base)
	{
		this.base = base;
		position = new DroolsPlayerPos(this);
	}

	public EntityPlayer getBase() {
		return base;
	}

	public void setBase(EntityPlayer base) {
		this.base = base;
	}

	public DroolsPlayerPos getPosition() {
		return position;
	}

	public void setPosition(DroolsPlayerPos position) {
		this.position = position;
	}
	
	

}
