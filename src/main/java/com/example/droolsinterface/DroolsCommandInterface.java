package com.example.droolsinterface;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

/**
 * 
 * Stub class. Used by drools to request actions from minecraft.
 * @author Samuel
 *
 */
public class DroolsCommandInterface
{
	
	/**
	 * Stub function.
	 * @param player
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void movePlayer(DroolsPlayer player, int x, int y, int z)
	{
		System.out.println("CALLED");
		player.base.setLocationAndAngles(x, y, z, 0, 0);//.moveEntity(x, y, z);
		
		
		/*
		 * 
		 * //TODO: This is stupid-awkward. It's because of the whole client-server
		 * synchronization thing. Find a way to handle it more elegantly.
		 * 
		 * //TODO: Find out if I even need to use that first move call.
		 * 
		 */
        
		if(player.base instanceof EntityPlayerMP)
		{
			EnumSet enumset = EnumSet.noneOf(S08PacketPlayerPosLook.EnumFlags.class);
			enumset.add(S08PacketPlayerPosLook.EnumFlags.X);
	        enumset.add(S08PacketPlayerPosLook.EnumFlags.Y);
	        enumset.add(S08PacketPlayerPosLook.EnumFlags.Z);
	        
			((EntityPlayerMP)player.base).playerNetServerHandler.setPlayerLocation(x, y, z, 0, 0, enumset);
		}
		
		System.out.println("CALLED2");
	}

}
