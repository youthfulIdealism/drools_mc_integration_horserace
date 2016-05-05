package org.drools.minecraft.util;

import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Item;
import org.drools.minecraft.model.Room;

/**
 *
 * Stub class. Used by drools to request actions from minecraft.
 *
 * @author Samuel
 *
 */
public class DroolsTestInterface
{
    public static boolean playerHasItem(Player player, String item)
    {
        for(Item inventoryItem : player.getInventory())
        {
            if(inventoryItem.getType().equals(item))
            {
                return true;
            }
        }
        return false;
    }
    
    public static boolean playerInRoom(Player player, Room room)
    {
        return player.getRoomsIn().contains(room);
    }
}
