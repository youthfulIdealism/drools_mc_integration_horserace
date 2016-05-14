/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class Room {

    private String id;

    private Location lowerBound;
    private Location upperBound;

    private int dimension;

    private List<Door> doors;

    private List<Location> chests;

    private List<IItem> items;

    public Room(String id) {
        this.id = id;
    }

    public Room(int x, int y, int z, int fx, int fy, int fz, String id) {
        lowerBound = new Location(Math.min(x, fx), Math.min(y, fy), Math.min(z, fz));
        upperBound = new Location(Math.max(x, fx), Math.max(y, fy), Math.max(z, fz));
        this.dimension = 0;
        this.id = id;
        doors = new ArrayList<Door>();
        chests = new ArrayList<Location>();
    }

    public Location getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Location lowerBound) {
        this.lowerBound = lowerBound;
    }

    public Location getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Location upperBound) {
        this.upperBound = upperBound;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }

    public void addDoor(Door door) {
        if (this.doors == null) {
            this.doors = new ArrayList<>();
        }
        this.doors.add(door);
    }

    public List<Location> getChests() {
        return chests;
    }

    public void setChests(List<Location> chests) {
        this.chests = chests;
    }

    public void addItem(IItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    public List<IItem> getItems() {
        return items;
    }

}
