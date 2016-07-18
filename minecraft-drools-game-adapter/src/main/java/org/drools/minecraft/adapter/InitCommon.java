/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 *
 * @author Samuel
 */
public class InitCommon {

    public static void preInit( FMLPreInitializationEvent event ) {
        MinecraftForge.EVENT_BUS.register( NewAdapter.getInstance() );
    }

    public static void init( FMLInitializationEvent event ) {

    }

    public static void postInit( FMLPostInitializationEvent event ) {

    }
}
