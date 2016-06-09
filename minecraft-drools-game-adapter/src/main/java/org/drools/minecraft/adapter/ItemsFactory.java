/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import java.util.HashMap;
import java.util.Map;
import org.drools.minecraft.model.BaseItem;
import org.drools.minecraft.model.Chest;
import org.drools.minecraft.model.Key;
import org.drools.minecraft.model.InventoryItem;

/**
 *
 * @author salaboy
 */
public class ItemsFactory {

    public static final Map<String, Class<? extends InventoryItem>> itemClasses = new HashMap<String, Class<? extends InventoryItem>>();

    static {
        {
            itemClasses.put("key", Key.class);

        }
    }

    /**
     * Generates a new InventoryItem given the canonical name of an item. If the item is not found within this mod,
     * a search is performed to try to find a corresponding item from vanilla minecraft or other registered mods, and
     * shoves them into a BaseItem.
     * @param itemName
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public static InventoryItem newItem(String itemName) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        Class referencedClass = itemClasses.get(itemName);
        InventoryItem newItem;
        
        if(referencedClass == null)
        {
            newItem = new BaseItem("", itemName);
        }else
        {
            String canonicalName = referencedClass.getCanonicalName();
            newItem = (InventoryItem) Class.forName(canonicalName).newInstance();
        }

        
        return newItem;
    }
    
    /*
    Obsoleted code:
    
    net.minecraft.item.Item stackItem = GameRegistry.findItem("minecraft", item.getType());
 -        if(stackItem == null)
 -        {
 +        if (stackItem == null) {
              stackItem = GameRegistry.findItem("examplemod", item.getType());
          }
 -        if(stackItem == null)
 -        {
 +        if (stackItem == null) {
              System.err.println("The item " + item.getType() + " could not be found. Try whacking the box a couple times--that usually helps.");
              return null;
          }
 -        
 -        
 -        ItemStack returnable = new ItemStack(stackItem, item.getCount(), item.getDurability());
 -        if(item.getName() != null)
 -        {
 +
 +        ItemStack returnable = new ItemStack(stackItem, 1, 1);
 +        if (item.getName() != null) {
              returnable.setStackDisplayName(item.getName());
          }
          return returnable;
    
    
    
    
    */
}
