package org.drools.minecraft.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stub class used to wrap the player.
 *
 * @author Samuel
 *
 */
public class Player {
    
    private List<Room> roomsIn;
    
    /*
    TODO: replace this with the Item interface instead
    */
    private List<Item> inventory;
    private boolean inventoryDirty;
    private Location location;

    public Player() {
        roomsIn = new ArrayList<>();
        inventory = new ArrayList<>();
        inventoryDirty = true;
        location = new Location(0, 0, 0);
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    
    
    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public boolean getInventoryDirty()
    {
        return inventoryDirty;
    }

    public void setInventoryDirty(boolean inventoryDirty)
    {
        this.inventoryDirty = inventoryDirty;
    }

    public List<Room> getRoomsIn() {
        return roomsIn;
    }

    public void setRoomsIn(List<Room> roomsIn) {
        this.roomsIn = roomsIn;
    }

}
