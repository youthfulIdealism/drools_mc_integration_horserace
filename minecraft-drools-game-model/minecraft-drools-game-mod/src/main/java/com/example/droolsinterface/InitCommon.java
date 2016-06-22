package com.example.droolsinterface;

import com.example.examplemod.items.ItemKey;
import com.example.examplemod.items.ItemRulerRoom;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitCommon
{
    public static ItemKey itemKey;
    public static ItemRulerRoom itemRulerRoom;
    
    
    
    public static void preInit(FMLPreInitializationEvent event)
    {
        itemKey = (ItemKey)(new ItemKey().setUnlocalizedName("key"));
        GameRegistry.registerItem(itemKey, "key");
        
        itemRulerRoom = (ItemRulerRoom)(new ItemRulerRoom().setUnlocalizedName("ruler_room"));
        GameRegistry.registerItem(itemRulerRoom, "ruler_room");
        
        org.drools.minecraft.adapter.InitCommon.preInit(event);
        //MinecraftForge.EVENT_BUS.register(new RulesDriver());
    }

    public static void init(FMLInitializationEvent event)
    {
        org.drools.minecraft.adapter.InitCommon.init(event);
    }

    public static void postInit(FMLPostInitializationEvent event)
    {
        org.drools.minecraft.adapter.InitCommon.postInit(event);
    }
}
