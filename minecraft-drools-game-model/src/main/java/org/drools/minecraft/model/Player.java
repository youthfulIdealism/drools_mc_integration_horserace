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
    private Inventory inventory;
    private ArrayList<Room> roomsIn;

    public Player() {
        inventory = new Inventory();
        roomsIn = new ArrayList<Room>();
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Room> getRoomsIn() {
        return roomsIn;
    }

    public void setRoomsIn(ArrayList<Room> roomsIn) {
        this.roomsIn = roomsIn;
    }

}
