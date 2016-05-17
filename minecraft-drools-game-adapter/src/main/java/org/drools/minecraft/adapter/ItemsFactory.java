/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import java.util.HashMap;
import java.util.Map;
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

    public static IItem newItem(String itemName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String canonicalName = itemClasses.get(itemName).getCanonicalName();
        return (IItem) Class.forName(canonicalName).newInstance();
    }
}
