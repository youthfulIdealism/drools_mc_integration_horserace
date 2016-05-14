/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.model;

/**
 *
 * @author Samuel
 */
public class Door {

    private Location lowerBound;
    private Location upperBound;
    private Room room;

    private String id;
    
    /* TODO
       The door should have a boolean for demarcating the status of the door.
    */

    public Door(String id) {
        this.id = id;
    }
    

    public Door(Location lowerBound, Location upperBound, Room room, String id) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.room = room;
        this.id = id;
    }

    public Door(int x, int y, int z, int fx, int fy, int fz, String id) {
        lowerBound = new Location(Math.min(x, fx), Math.min(y, fy), Math.min(z, fz));
        upperBound = new Location(Math.max(x, fx), Math.max(y, fy), Math.max(z, fz));
        this.id = id;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
