package com.example.droolsinterface;

import java.util.ArrayList;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.example.examplemod.ExampleMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

/**
 * Driver to continually submit updated information to drools.
 * I have a vague suspicion that this is really clumsy, and that
 * there's a better way to go about it.
 * 
 * The class currently updates drools once every 16 ticks.
 * 
 * If you have any ideas, I'd be more than happy to hear them. :)
 * @author Samuel
 *
 */
public class RulesDriver
{
	int throttle = 0;
	
	
	public static KieSession kSession;
	
	public static DroolsPlayerPos playerPos;
	public static FactHandle stateHandle;
	
	public RulesDriver()
	{
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			kSession = kContainer.newKieSession("ksession-rules");
			
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
			if(event.world.playerEntities.size() > 0)
			{
				EntityPlayer player = event.world.playerEntities.get(0);
				BlockPos loc = player.getPosition();
				playerPos = new DroolsPlayerPos(new DroolsPlayer(player), loc.getX(), loc.getY(), loc.getZ());
				if(stateHandle != null)
				{
					kSession.delete(stateHandle);
				}
				stateHandle = kSession.insert(playerPos);
			}
			
			
			kSession.fireAllRules();
		}
	}

}
