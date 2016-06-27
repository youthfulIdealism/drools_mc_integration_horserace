/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import org.drools.minecraft.helper.CommandHelper;
import org.drools.minecraft.helper.GlobalHelper;
import org.drools.minecraft.model.Chest;
import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.InventoryItem;
import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.Mob;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;

/**
 *
 * @author Samuel
 */
public class NotificationManager
{
    private GlobalHelper helper;
    
    public NotificationManager(GlobalHelper helper)
    {
        this.helper = helper;
    }
    
    /**
     * Evaluate and act upon any queued notifications.
     * @param world 
     */
    public void update(World world)
    {
        Queue<GlobalHelper.Notification> tasks = helper.getNotificationQueue();
        while(tasks.peek() != null)
        {
            GlobalHelper.Notification current = tasks.poll();
            String[] parsedIndicator = current.getData().split(" ");
            if(parsedIndicator[0].equals("DOOR"))
            {
                handleDoorChange(parsedIndicator, current.getObject(), world);
            }else if(parsedIndicator[0].equals("GENERATE"))
            {
                handleGenerate(parsedIndicator, current.getObject(), world);
            }else if(parsedIndicator[0].equals("CHEST"))
            {
                handleChest(parsedIndicator, current.getObject(), world);
            }else if(parsedIndicator[0].equals("PLAYER"))
            {
                handlePlayer(parsedIndicator, current.getObject(), world);
            }else if(parsedIndicator[0].equals("CHAT"))
            {
                handleChat(parsedIndicator, current.getObject(), world);
            }else if(parsedIndicator[0].equals("MOB"))
            {
                handleMob(parsedIndicator, current.getObject(), world);
            }
        }
    }
    
    private void handleDoorChange(String[] parsedIndicator, List<Object> ParamList, World world)
    {
        Door door = (Door)(ParamList.get(0));
        if(parsedIndicator[1].equals("OPEN"))
        {
           UtilTerrainEdit.constructDoor(world, door, false);
        }else if(parsedIndicator[1].equals("CLOSED"))
        {
            UtilTerrainEdit.constructDoor(world, door, true);
        }
    }
    
    private void handleGenerate(String[] parsedIndicator, List<Object> ParamList, World world)
    {
        if(parsedIndicator[1].equals("ROOM"))
        {
            UtilTerrainEdit.constructRoom(world, (Room)(ParamList.get(0)));
        }else if(parsedIndicator[1].equals("CHEST"))
        {
            UtilTerrainEdit.placeChest(world, (Chest)(ParamList.get(0)));
        }else if(parsedIndicator[1].equals("DOOR"))
        {
            UtilTerrainEdit.constructDoor(world, (Door)(ParamList.get(0)), true);
        }
    }
    
    private void handleChest(String[] parsedIndicator, List<Object> ParamList, World world)
    {
        if(parsedIndicator[1].equals("PUTITEM"))
        {
            UtilTerrainEdit.addChestItem(world, (Chest)(ParamList.get(0)), (InventoryItem)(ParamList.get(1)));
        }
    }
    
    private void handlePlayer(String[] parsedIndicator, List<Object> ParamList, World world)
    {
        if(parsedIndicator[1].equals("TELEPORT"))
        {
            for(EntityPlayer player : world.playerEntities)
            {
                if(player.getName().equals(((Player) ParamList.get(0)).getName()))
                {
                    Location location = (Location)(ParamList.get(1));
                    player.fallDistance = 0;
                    player.setPositionAndUpdate(location.getX(), location.getY(), location.getZ());
                    return;
                }
            }
            System.out.println("ERROR: player " + ((Player) ParamList.get(0)).getName() + " not found when teleportation was attempted.");
        }else if(parsedIndicator[1].equals("HEALTH"))
        {
            for(EntityPlayer player : world.playerEntities)
            {
                if(player.getName().equals(((Player) ParamList.get(0)).getName()) && player.getHealth() > 0)
                {
                    int health = ((Integer)ParamList.get(1)).intValue();
                    player.setHealth(health);
                    return;
                }
            }
            System.out.println("ERROR: player " + ((Player) ParamList.get(0)).getName() + " not found when health set was attempted.");
        }else if(parsedIndicator[1].equals("INVENTORY_CLEAR"))
        {
            for(EntityPlayer player : world.playerEntities)
            {
                if(player.getName().equals(((Player) ParamList.get(0)).getName()))
                {
                    player.inventory.clear();
                    return;
                }
            }
            System.out.println("ERROR: player " + ((Player) ParamList.get(0)).getName() + " not found when health set was attempted.");
        }else if(parsedIndicator[1].equals("EFFECT"))
        {
            for(EntityPlayer player : world.playerEntities)
            {
                if(player.getName().equals(((Player) ParamList.get(0)).getName()))
                {
                    int id = ((CommandHelper.Effect)ParamList.get(1)).effectid;
                    int duration = (Integer)ParamList.get(2);
                    int power = (Integer)ParamList.get(3) - 1;
                    player.addPotionEffect(new PotionEffect(id, duration * 20, power));
                    return;
                }
            }
            System.out.println("ERROR: player " + ((Player) ParamList.get(0)).getName() + " not found when health set was attempted.");
        }
    }
    
    private void handleChat(String[] parsedIndicator, List<Object> ParamList, World world)
    {
        if(parsedIndicator[1].equals("BROADCAST"))
        {
            for(EntityPlayer player : world.playerEntities)
            {
                player.addChatComponentMessage(new ChatComponentText((String)ParamList.get(0)));
            }
        }
    }
    
    private void handleMob(String[] parsedIndicator, List<Object> ParamList, World world)
    {
        if(parsedIndicator[1].equals("SPAWN"))
        {
            try
            {
                EntityLiving entity = MobFactory.newCreature((Mob.MobTypes) ParamList.get(0), world);
                Location location = (Location) ParamList.get(1);
                entity.setPositionAndUpdate(location.getX(), location.getY(), location.getZ());
                world.spawnEntityInWorld(entity);
            
            
            
            
            
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex)
            {
                Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex)
            {
                Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex)
            {
                Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex)
            {
                Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex)
            {
                Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}