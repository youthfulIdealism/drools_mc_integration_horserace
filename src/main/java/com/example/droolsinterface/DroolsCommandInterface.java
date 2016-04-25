package com.example.droolsinterface;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionAbsorption;
import net.minecraft.potion.PotionAttackDamage;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealth;
import net.minecraft.potion.PotionHealthBoost;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

/**
 * 
 * Stub class. Used by drools to request actions from minecraft.
 * @author Samuel
 *
 */
public class DroolsCommandInterface
{
	//TODO: complete list for rule-writer reference
	public static final int potionSpeed = Potion.moveSpeed.id;
	public static final int potionSlow = Potion.moveSlowdown.id;
	public static final int potionRegen = Potion.regeneration.id;
	public static final int potionSaturation = Potion.saturation.id;
	
	//TODO: make a complete block list for rule-writer reference, so that they don't have to go through minecraft's blocks.
	
	
	
	
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
	}
	
	public static void sendChat(String chat)
	{
		if(RulesDriver.world.playerEntities.size() > 0)
		{
			RulesDriver.world.playerEntities.get(0).addChatMessage(new ChatComponentText(chat));
		}
	}
	
	public static void spawnEntity(String entityid, int x, int y, int z)
	{
		Entity entity = EntityList.createEntityByName(entityid, RulesDriver.world);
		entity.setLocationAndAngles(x, y, z,0.0F, 0.0F);
		RulesDriver.world.spawnEntityInWorld(entity);
	}
	
	public static void enchantEntity(EntityLiving entity, int potion, int duration, int strength)
	{
		entity.addPotionEffect(new PotionEffect(potion, duration, strength));
	}
	
	public static void enchantEntity(EntityPlayer entity, int potion, int duration, int strength)
	{
		entity.addPotionEffect(new PotionEffect(potion, duration, strength));
	}
	
	public static boolean blockMatches(int x, int y, int z, BlockState match)
	{
		IBlockState state = RulesDriver.world.getBlockState(new BlockPos(x, y, z));
		System.out.println(state);
		return match.getBlock().isAssociatedBlock(state.getBlock());
		//entity.addPotionEffect(new PotionEffect(potion, duration, strength));
	}

}
