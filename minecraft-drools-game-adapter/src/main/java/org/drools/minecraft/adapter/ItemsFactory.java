///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.drools.minecraft.adapter;
//
//import java.util.HashMap;
//import java.util.Map;
//import org.drools.minecraft.model.BaseItem;
//import org.drools.minecraft.model.Key;
//import org.drools.minecraft.model.InventoryItem;
//
///**
// *
// * @author salaboy
// */
//public class ItemsFactory {
//
//    public static final Map<String, Class<? extends InventoryItem>> itemClasses = new HashMap<String, Class<? extends InventoryItem>>();
//
//    static {
//        {
//            itemClasses.put("item.key", Key.class);
//
//        }
//    }
//
//    /**
//     * Generates a new InventoryItem given the canonical name of an item. If the item is not found within this mod,
//     * a search is performed to try to find a corresponding item from vanilla minecraft or other registered mods, and
//     * shoves them into a BaseItem.
//     * @param itemName
//     * @param displayName
//     * @return
//     * @throws ClassNotFoundException
//     * @throws InstantiationException
//     * @throws IllegalAccessException 
//     */
//    public static InventoryItem newItem(String itemName, String displayName) throws ClassNotFoundException, InstantiationException, IllegalAccessException
//    {
//        Class referencedClass = itemClasses.get(itemName);
//        InventoryItem newItem;
//        
//        if(referencedClass == null)
//        {
//            newItem = new BaseItem("", itemName);
//        }else
//        {
//            String canonicalName = referencedClass.getCanonicalName();
//            newItem = (InventoryItem) Class.forName(canonicalName).newInstance();
//        }
//        newItem.setName(displayName);
//
//        return newItem;
//    }
//}
