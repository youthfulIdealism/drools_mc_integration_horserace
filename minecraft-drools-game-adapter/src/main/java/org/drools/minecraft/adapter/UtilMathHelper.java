/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;

/**
 *
 * @author Samuel
 */
public class UtilMathHelper
{
    /**
     * helper method. Determines if a player is mathematically
     * within a room.
     * @param player
     * @param room
     * @return 
     */
    public static boolean playerWithinRoom(Player player, Room room) {
        if(player == null)
        {
            System.out.println("ERROR: Player not found in playerWithinRoom.");
            return false;
        }
        Location playerLoc = player.getLocation();
        Location roomLowerLoc = room.getLowerBound();
        Location roomUpperLoc = room.getUpperBound();
        boolean xWithin = within(playerLoc.getX(), roomLowerLoc.getX(), roomUpperLoc.getX());
        boolean yWithin = within(playerLoc.getY(), roomLowerLoc.getY(), roomUpperLoc.getY());
        boolean zWithin = within(playerLoc.getZ(), roomLowerLoc.getZ(), roomUpperLoc.getZ());
        return xWithin && yWithin && zWithin;
    }

    /**
     * helper method. Determines if a number is within bounds.
     * @param number
     * @param first
     * @param second
     * @return 
     */
    public static boolean within(int number, int first, int second) {
        int min = Math.min(first, second);
        int max = Math.max(first, second);
        return number >= min && number <= max;
    }
}
