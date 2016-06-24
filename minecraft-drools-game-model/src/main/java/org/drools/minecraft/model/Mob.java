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
public class Mob
{
    private MobTypes type;
    private String id;
    private Location location;
    private int roomcount;
    
    /**
     *
     */
    public enum MobTypes
    {
        BAT, CHICKEN, COW, MOOSHROOM, PIG, RABBIT, SHEEP, SQUID, VILLAGER,
        POISON_SPIDER, ENDERMAN, SPIDER, ZOMBIE_PIGMAN,
        BLAZE, CREEPER, ELDER_GUARDIAN, ENDERMITE, GHAST, GUARDIAN, MAGMA_CUBE,
        SILVERFISH, SKELETON, SLIME, WITCH, WITHER_SKELETON, ZOMBIE, ZOMBIE_VILLAGER,
        DONKEY, HORSE, MULE, OCELOT, WOLF, IRON_GOLEM, SNOW_GOLEM,
        ENDER_DRAGON, WITHER,
        UNKNOWN
    
    }

    public Mob(MobTypes type, String id, Location location)
    {
        this.type = type;
        this.id = id;
        this.location = location;
        roomcount = 0;
    }

    public MobTypes getType()
    {
        return type;
    }

    public void setType(MobTypes type)
    {
        this.type = type;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }
    
    
    

    public int getRoomcount()
    {
        return roomcount;
    }

    public void setRoomcount(int roomcount)
    {
        this.roomcount = roomcount;
    }
    
    
}
