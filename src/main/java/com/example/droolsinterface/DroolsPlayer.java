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
	
	public DroolsPlayer(EntityPlayer base)
	{
		this.base = base;
	}

}
