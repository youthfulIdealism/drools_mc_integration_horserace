/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.drools.minecraft.model.DroolsPlayer;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;
import org.drools.minecraft.model.Session;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 *
 * @author rs
 */
public class Adapter {

    int throttle = 0;

    public static KieSession kSession;
    //public static FactHandle stateHandle;

    Adapter adapter;

    //TODO: this has to change, if we want rules accesible from different dimensions.
    //public static World world;
    ArrayList<DroolsPlayer> players;

    public Adapter() {
        if(adapter != null)
        {
            System.err.println("Hey, bub, you tried to build two adapters. I don't like it when you do that. In retribution, I think I'll leak memory unboundedly for a while.");
        }
        adapter = this;
        
        new Player();
        System.out.println("Something Happened!");
        System.out.println("Something Happened!");
        System.out.println("Something Happened!");
        System.out.println("Something Happened!");
        System.out.println("Something Happened!");
        System.out.println("Something Happened!");
        System.out.println("Something Happened!");

        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            kSession = kContainer.newKieSession("ksession-rules");

            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        //kSession.insert(this);
        
        Room defaultRoom = new Room(-100, -100, -100, 100, 100, 100);
        kSession.insert(defaultRoom);
    }

    public void update(World world) {
        //adapter.update(event.world);
       /* players = new ArrayList<DroolsPlayer>();

        if (event.world.playerEntities.size() > 0) {

            if (stateHandle != null) {
                kSession.delete(stateHandle);
            }

            //TODO: create Session, have adapter change session based on players.
            EntityPlayer player = event.world.playerEntities.get(0);
            stateHandle = kSession.insert(players.get(0));
        }

        //TODO: We shouldn't be creating tick events. The adapter should handle that.
        Event tickEvent = new Event("tick");
        kSession.insert(tickEvent);*/

        kSession.fireAllRules();
    }
    
    @SubscribeEvent
    public void onServerTick(TickEvent.WorldTickEvent event) {

        throttle++;
        if (throttle % 16 == 0) {

            //for simplicity's sake, this locks the adapter into only working
            //in the default dimension. Rules will not work in the nether or end.
            if (event.world.provider.getDimensionId() == 0) {
                adapter.update(event.world);
            }
        }
    }
    
    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event)
    {
        if(event.entity instanceof EntityPlayer)
        {
            kSession.insert(new Session(new DroolsPlayer()));
        }
    }

}
