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
import org.drools.minecraft.model.IItem;
import org.drools.minecraft.model.Key;

/**
 *
 * @author salaboy
 */
public class ItemsFactory {

    public static final Map<String, Class<? extends IItem>> itemClasses = new HashMap<String, Class<? extends IItem>>();

    static {
        {
            itemClasses.put("key", Key.class);
            itemClasses.put("chest", Chest.class);

        }
    }

    public static IItem newItem(String itemName) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        Class referencedClass = itemClasses.get(itemName);
        IItem newItem;
        boolean instantiatingBaseItem = false;
        
        if(referencedClass == null)
        {
            instantiatingBaseItem = true;
            newItem = new BaseItem("", itemName);
            
            
        }else
        {
            String canonicalName = referencedClass.getCanonicalName();
            newItem = (IItem) Class.forName(canonicalName).newInstance();
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
