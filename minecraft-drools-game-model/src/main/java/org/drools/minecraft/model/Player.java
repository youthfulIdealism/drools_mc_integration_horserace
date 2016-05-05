package org.drools.minecraft.model;

import java.util.ArrayList;

/**
 * Stub class used to wrap the player.
 *
 * @author Samuel
 *
 */
public class Player {

    private int xloc;
    private int yloc;
    private int zloc;
    //private Inventory inventory;
    private ArrayList<Room> roomsIn;
    private ArrayList<Item> inventory;
    private boolean inventoryDirty;

    public Player() {
        //inventory = new Inventory();
        roomsIn = new ArrayList<Room>();
        inventory = new ArrayList<Item>();
        inventoryDirty = true;
    }

    public int getXloc() {
        return xloc;
    }

    public void setXloc(int xloc) {
        this.xloc = xloc;
    }

    public int getYloc() {
        return yloc;
    }

    public void setYloc(int yloc) {
        this.yloc = yloc;
    }

    public int getZloc() {
        return zloc;
    }

    public void setZloc(int zloc) {
        this.zloc = zloc;
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
    
    /*public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }*/

    public ArrayList<Room> getRoomsIn() {
        return roomsIn;
    }

    public void setRoomsIn(ArrayList<Room> roomsIn) {
        this.roomsIn = roomsIn;
    }

}
