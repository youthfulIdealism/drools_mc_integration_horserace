package org.drools.minecraft.util;

import org.drools.minecraft.model.Door;
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
        for (Item inventoryItem : player.getInventory())
        {
            if (inventoryItem.getType().equals(item))
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

    public static boolean playerTouchingDoor(Player player, Door door)
    {
        if (player.getXloc() >= door.getX() - 1 && player.getXloc() <= door.getFx() + 1)
        {
            if (player.getYloc() >= door.getY() - 1 && player.getYloc() <= door.getFy() + 1)
            {
                if (player.getZloc() >= door.getZ() - 1 && player.getZloc() <= door.getFz() + 1)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
