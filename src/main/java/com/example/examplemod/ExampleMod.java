package com.example.examplemod;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
	// The instance of your mod that Forge uses.  Optional.
	@Mod.Instance(ExampleMod.MODID)
	public static ExampleMod instance;
	
	public static final String MODID = "examplemod";
	public static final String VERSION = "1.0";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		com.example.droolsinterface.InitCommon.preInit(event);
		com.example.droolsinterface.InitClient.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		com.example.droolsinterface.InitCommon.init(event);
		com.example.droolsinterface.InitClient.init(event);
		
		// some example code
		System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());

		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		com.example.droolsinterface.InitCommon.postInit(event);
		com.example.droolsinterface.InitClient.postInit(event);
	}




	public static class Message {

		public static final int HELLO = 0;
		public static final int GOODBYE = 1;

		private String message;

		private int status;

		public String getMessage() {
			return this.message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return this.status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String toString()
		{
			return message;
		}

	}

}
