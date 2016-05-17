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

    private String name; 
    private List<Room> roomsIn;
    private List<IItem> inventory;
    private boolean inventoryDirty;
    private Location location;

    public Player() {
        roomsIn = new ArrayList<>();
        inventory = new ArrayList<>();
        inventoryDirty = true;
        location = new Location(0, 0, 0);
    }

    public Player(String name) {
        this();
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<IItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<IItem> inventory) {
        this.inventory = inventory;
    }

    public boolean getInventoryDirty() {
        return inventoryDirty;
    }

    public void setInventoryDirty(boolean inventoryDirty) {
        this.inventoryDirty = inventoryDirty;
    }

    public List<Room> getRoomsIn() {
        return roomsIn;
    }

    public void setRoomsIn(List<Room> roomsIn) {
        this.roomsIn = roomsIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
