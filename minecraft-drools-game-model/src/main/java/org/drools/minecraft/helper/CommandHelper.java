/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.helper;

import org.drools.minecraft.model.Chest;
import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.InventoryItem;
import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;

/**
 *
 * @author salaboy
 */
public interface CommandHelper {
    
    public void notifyOpenDoor(Door door);
    
    public void notifyCloseDoor(Door door);
    
    public void notifyTeleportPlayer(Player player, Location location);
    
    public void notifyGenerateDoor(Door door);
    
    public void notifyGenerateRoom(Room room);
    
    public void notifyGenerateChest(Chest chest);
    
    public void notifyItemToChest(Chest chest, InventoryItem item);
    
    public void notifySetPlayerHealth(Player player, Integer health);
    
    public void notifyChat(String contents);
}
