package org.drools.minecraft.model;

import java.util.ArrayList;

/**
 * Stub class used to wrap the player.
 *
 * @author Samuel
 *
 */
public class Player {
    
    private ArrayList<Room> roomsIn;
    private ArrayList<Item> inventory;
    private boolean inventoryDirty;
    private Location location;

    public Player() {
        roomsIn = new ArrayList<Room>();
        inventory = new ArrayList<Item>();
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

    
    
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
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

    public ArrayList<Room> getRoomsIn() {
        return roomsIn;
    }

    public void setRoomsIn(ArrayList<Room> roomsIn) {
        this.roomsIn = roomsIn;
    }

}
