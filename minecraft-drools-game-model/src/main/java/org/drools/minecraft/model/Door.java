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
public class Door
{
    private int x;
    private int y;
    private int z;
    private int fx;
    private int fy;
    private int fz;
    private Room room;
    String id;

    public Door(int x, int y, int z, int fx, int fy, int fz, String id)
    {
        this.x = Math.min(x, fx);
        this.y = Math.min(y, fy);
        this.z = Math.min(z, fz);
        this.fx = Math.max(x, fx);
        this.fy = Math.max(y, fy);
        this.fz = Math.max(z, fz);
        this.id = id;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getZ()
    {
        return z;
    }

    public void setZ(int z)
    {
        this.z = z;
    }

    public int getFx()
    {
        return fx;
    }

    public void setFx(int fx)
    {
        this.fx = fx;
    }

    public int getFy()
    {
        return fy;
    }

    public void setFy(int fy)
    {
        this.fy = fy;
    }

    public int getFz()
    {
        return fz;
    }

    public void setFz(int fz)
    {
        this.fz = fz;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    
    

}
