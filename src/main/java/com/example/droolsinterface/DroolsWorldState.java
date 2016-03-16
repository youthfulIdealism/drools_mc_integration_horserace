package com.example.droolsinterface;

import java.util.*;

import net.minecraft.entity.player.EntityPlayer;

public class DroolsWorldState
{
	public ArrayList<EntityPlayer> players;
	
	public DroolsWorldState()
	{
		players = new ArrayList<EntityPlayer>();
	}
	
	public ArrayList<EntityPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<EntityPlayer> players) {
		this.players = players;
	}
	

}
