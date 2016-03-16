package com.example.droolsinterface;

import java.util.ArrayList;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.ExampleMod.Message;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class RulesDriver
{
	int throttle = 0;
	
	
	public static KieSession kSession;
	
	public static Message message;
	public static FactHandle messageHandle;
	
	public static DroolsWorldState state;
	public static FactHandle stateHandle;
	
	public RulesDriver()
	{
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			kSession = kContainer.newKieSession("ksession-rules");

			// go !
			message = new Message();
			message.setMessage("Hello World");
			message.setStatus(Message.HELLO);
			messageHandle = kSession.insert(message);
			
			state = new DroolsWorldState();
			
			kSession.fireAllRules();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	@SubscribeEvent
	public void onServerTick(WorldTickEvent event)
	{
		throttle++;
		if(throttle % 16 == 0)
		{
			System.out.println("tick");
			//message.setMessage("Hello World");
			//message.setStatus(Message.HELLO);
			if(messageHandle != null)
			{
				kSession.delete(messageHandle);
			}
			messageHandle = kSession.insert(message);
			
			
			/*state.players = (ArrayList<EntityPlayer>) event.world.playerEntities;
			if(stateHandle != null)
			{
				kSession.delete(stateHandle);
			}
			stateHandle = kSession.insert(state);*/
			
			kSession.fireAllRules();
		}
	}

}
