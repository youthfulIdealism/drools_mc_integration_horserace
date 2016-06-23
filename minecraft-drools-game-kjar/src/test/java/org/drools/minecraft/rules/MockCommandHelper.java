/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.rules;

import org.drools.minecraft.model.Door;
import org.drools.minecraft.helper.CommandHelper;
import org.drools.minecraft.model.Chest;
import org.drools.minecraft.model.InventoryItem;
import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;

/**
 *
 * @author salaboy
 */
public class MockCommandHelper implements CommandHelper {

    @Override
    public void notifyOpenDoor(Door door) {
        System.out.println("Opening Door: " + door);
    }

    @Override
    public void notifyCloseDoor(Door door) {
        System.out.println("Closing Door: " + door);
    }

    @Override
    public void notifyTeleportPlayer(Player player, Location location)
    {
        System.out.println("Teleporting player " + player.getName() + " to location " + location);
    }

    @Override
    public void notifyGenerateDoor(Door door)
    {
        System.out.println("Generating door " + door.getId());
    }

    @Override
    public void notifyGenerateRoom(Room room)
    {
         System.out.println("Generating room " + room.getId());
    }

    @Override
    public void notifyGenerateChest(Chest chest)
    {
        System.out.println("Generating chest at " + chest.getLocation());
    }

    @Override
    public void notifyItemToChest(Chest chest, InventoryItem item)
    {
        System.out.println("Placing item " + item.getName() + " of type " + item.getType() + " in a chest at " + chest.getLocation());
    }

    @Override
    public void notifySetPlayerHealth(Player player, Integer health)
    {
        System.out.println("Setting health of player " + player + " to " + health);
    }

    @Override
    public void notifyChat(String contents)
    {
        System.out.println("Broadcasting chat message: " + contents);
    }

    @Override
    public void notifyClearPlayerInventory(Player player)
    {
        System.out.println("Clearing the inventory of: " + player.getName());
    }

    @Override
    public void notifyEffectPlayer(Player player, Effect effect, int duration, int power)
    {
        System.out.println("Enchanting player " + player.getName() + " with effect " + effect + " for " + duration + " seconds at power " + power);
    }

}
