/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.model;

import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class Room
{
    Location lowerBound;
    Location upperBound;
    
    private int dimension;
    private String id;
    
    private ArrayList<Door> doors;
    private ArrayList<Location> chests;

    public Room(int x, int y, int z, int fx, int fy, int fz, String id) {
        lowerBound = new Location(Math.min(x, fx), Math.min(y, fy), Math.min(z, fz));
        upperBound = new Location(Math.max(x, fx), Math.max(y, fy), Math.max(z, fz));
        this.dimension = 0;
        this.id = id;
        doors = new ArrayList<Door>();
        chests = new ArrayList<Location>();
    }

    public Location getLowerBound()
    {
        return lowerBound;
    }

    public void setLowerBound(Location lowerBound)
    {
        this.lowerBound = lowerBound;
    }

    public Location getUpperBound()
    {
        return upperBound;
    }

    public void setUpperBound(Location upperBound)
    {
        this.upperBound = upperBound;
    }
    
    public int getDimension()
    {
        return dimension;
    }

    public void setDimension(int dimension)
    {
        this.dimension = dimension;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public ArrayList<Door> getDoors()
    {
        return doors;
    }

    public void setDoors(ArrayList<Door> doors)
    {
        this.doors = doors;
    }

    public ArrayList<Location> getChests()
    {
        return chests;
    }

    public void setChests(ArrayList<Location> chests)
    {
        this.chests = chests;
    }
    
}
