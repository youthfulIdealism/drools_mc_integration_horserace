package com.example.droolsinterface;

import org.drools.minecraft.model.DroolsPlayer;
import org.drools.minecraft.model.Event;
import java.util.ArrayList;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import org.drools.minecraft.adapter.Adapter;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.util.GameUtil;

/**
 * This is being replaced by the adapter.
 *
 * @author Samuel
 *
 */
@Deprecated
public class RulesDriver {
    public RulesDriver() {
        
    }
}
