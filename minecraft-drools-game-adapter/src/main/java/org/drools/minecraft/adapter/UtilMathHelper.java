/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.drools.game.capture.flag.model.Location;
import org.drools.game.capture.flag.model.Zone;

/**
 *
 * @author Samuel
 */
public class UtilMathHelper {

    /**
     * helper method. Determines if a player is mathematically
     * within a zone.
     *
     * @param player
     * @param zone
     *
     * @return
     */
    public static boolean playerWithinZone( Location playerLoc, Zone zone ) {
        Location roomLowerLoc = zone.getLowerBound();
        Location roomUpperLoc = zone.getUpperBound();
        boolean xWithin = within( playerLoc.getX(), roomLowerLoc.getX(), roomUpperLoc.getX() );
        boolean yWithin = within( playerLoc.getY(), roomLowerLoc.getY(), roomUpperLoc.getY() );
        boolean zWithin = within( playerLoc.getZ(), roomLowerLoc.getZ(), roomUpperLoc.getZ() );
        return xWithin && yWithin && zWithin;
    }

    /**
     * helper method. Determines if a number is within bounds.
     *
     * @param number
     * @param first
     * @param second
     *
     * @return
     */
    private static boolean within( int number, int first, int second ) {
        int min = Math.min( first, second );
        int max = Math.max( first, second );
        return number >= min && number <= max;
    }

    public static boolean playerPickedTheFlag( EntityPlayer player ) {

        for ( int i = 0; i < player.inventory.mainInventory.length; i++ ) {
            ItemStack stack = player.inventory.mainInventory[i];
            if ( stack != null ) {
                System.out.println( "Item: " + stack.getUnlocalizedName() + " -  " + stack.getDisplayName() );
                if ( stack.getUnlocalizedName().equals( "Flag" ) ) {
                    return true;
                }
//                    try {
//                        player.getInventory().add(ItemsFactory.newItem(stack.getUnlocalizedName(), stack.getDisplayName()));
//                    } catch (Exception ex) {
//                        Logger.getLogger(UtilMathHelper.class.getName()).log(Level.SEVERE, null, ex);
//                    }
            }
        }
        return false;

//            for (int i = 0; i < player.inventory.armorInventory.length; i++) {
//                ItemStack stack = player.inventory.armorInventory[i];
//                if (stack != null) {
//                    try {
//                        player.getInventory().add(ItemsFactory.newItem(stack.getUnlocalizedName(), stack.getDisplayName()));
//                    } catch (Exception ex) {
//                        Logger.getLogger(UtilMathHelper.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
    }

}
