package com.example.examplemod.util;

import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.IItem;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.Room;
import org.drools.minecraft.helper.TestHelper;

/**
 *
 * Stub class. Used by drools to request actions from minecraft.
 *
 * @author Samuel
 *
 */
public class MinecraftTestHelper implements TestHelper {

    public static boolean playerHasItem(Player player, String item) {
        for (IItem inventoryItem : player.getInventory()) {
            if (inventoryItem.getType().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public static boolean playerInRoom(Player player, Room room) {
        return player.getRoomsIn().contains(room);
    }

    public static boolean playerTouchingDoor(Player player, Door door) {
        Location playerLoc = player.getLocation();

        Location doorLower = door.getLowerBound();
        Location doorUpper = door.getUpperBound();
        if (playerLoc.getX() >= doorLower.getX() - 1 && playerLoc.getX() <= doorUpper.getX() + 1) {
            if (playerLoc.getY() >= doorLower.getY() - 1 && playerLoc.getY() <= doorUpper.getY() + 1) {
                if (playerLoc.getZ() >= doorLower.getZ() - 1 && playerLoc.getZ() <= doorUpper.getZ() + 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
