package com.example.droolsinterface;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class InitCommon
{
	public static void preInit(FMLPreInitializationEvent event)
	{
		//TODO: add side:server only?
		MinecraftForge.EVENT_BUS.register(new RulesDriver());
	}
	
	public static void init(FMLInitializationEvent event)
	{
		
	}

	public static void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
