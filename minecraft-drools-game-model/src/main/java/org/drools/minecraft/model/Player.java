package org.drools.minecraft.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Samuel
 *
 */
public class Player {
    
    /*
    //TODO: Find out whether it would be appropriate for player to extend mob.
    
    
    */

    private String name; 
    private List<Room> roomsIn;
    private List<InventoryItem> inventory;
    private boolean inventoryDirty;
    private Location location;
    
    private float hunger;
    private float health;
    private float experience;
    private boolean isDead;
    private boolean isBurning;

    public Player() {
        roomsIn = new ArrayList<>();
        inventory = new ArrayList<>();
        inventoryDirty = true;
        location = new Location(0, 0, 0);
        hunger = 0;
        health = 20;
        experience = 0;
        isDead = false;
        isBurning = false;
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

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItem> inventory) {
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

    public float getHunger()
    {
        return hunger;
    }

    public void setHunger(float hunger)
    {
        this.hunger = hunger;
    }

    public float getHealth()
    {
        return health;
    }

    public void setHealth(float health)
    {
        this.health = health;
    }

    public float getExperience()
    {
        return experience;
    }

    public void setExperience(float experience)
    {
        this.experience = experience;
    }

    public boolean isIsDead()
    {
        return isDead;
    }

    public void setIsDead(boolean isDead)
    {
        this.isDead = isDead;
    }

    public boolean isIsBurning()
    {
        return isBurning;
    }

    public void setIsBurning(boolean isBurning)
    {
        this.isBurning = isBurning;
    }
    
    

}
