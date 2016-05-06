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
public class Chest
{
    private int xloc;
    private int yloc;
    private int zloc;
    private ArrayList<Item> inventory;

    public Chest(int xloc, int yloc, int zloc)
    {
        this.xloc = xloc;
        this.yloc = yloc;
        this.zloc = zloc;
        this.inventory = new ArrayList<Item>();
    }

    public int getXloc()
    {
        return xloc;
    }

    public void setXloc(int xloc)
    {
        this.xloc = xloc;
    }

    public int getYloc()
    {
        return yloc;
    }

    public void setYloc(int yloc)
    {
        this.yloc = yloc;
    }

    public int getZloc()
    {
        return zloc;
    }

    public void setZloc(int zloc)
    {
        this.zloc = zloc;
    }

    public ArrayList<Item> getInventory()
    {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory)
    {
        this.inventory = inventory;
    }
    
    
}
