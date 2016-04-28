/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.drools.minecraft.model.Room;
import org.kie.api.runtime.KieSession;

/**
 *
 * @author rs
 */
public class Adapter {

    public static KieSession kSession;

    public Adapter(KieSession session) {
        kSession = session;
        
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

}
