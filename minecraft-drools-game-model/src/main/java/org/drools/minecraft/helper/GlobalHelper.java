/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.drools.minecraft.model.Chest;
import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.InventoryItem;
import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;

/**
 *
 * @author Samuel
 */
public class GlobalHelper implements CommandHelper
{
   
    private Queue<Notification> notificationQueue;

    /**
     * Represents a notification to minecraft. Contains the data that is being acted upon,
     * and a string to instruct Minecraft on how to react to that data.
     */
    public class Notification
    {
        private String data;
        private List<Object> object;
        public Notification(String data, List<Object> object)
        {
            this.data = data;
            this.object = object;
        }
        
        public Notification(String data, Object object)
        {
            this.data = data;
            this.object = new ArrayList<Object>(1);
            this.object.add(object);
        }
        
        public Notification(String data, Object object1, Object object2)
        {
            this.data = data;
            this.object = new ArrayList<Object>(2);
            this.object.add(object1);
            this.object.add(object2);
        }

        public String getData()
        {
            return data;
        }

        public List<Object> getObject()
        {
            return object;
        }
    }
    
    public GlobalHelper()
    {
        notificationQueue = new LinkedList<Notification>();
    }

    @Override
    public void notifyOpenDoor(Door door)
    {
        String data = "DOOR OPEN";
        notificationQueue.add(new Notification(data, door));
    }

    @Override
    public void notifyCloseDoor(Door door)
    {
        String data = "DOOR CLOSE";
        notificationQueue.add(new Notification(data, door));
    }
    
    @Override
    public void notifyGenerateRoom(Room room)
    {
        Notification roomNotification = new Notification("GENERATE ROOM", room);
        notificationQueue.add(roomNotification);
    }

    @Override
    public void notifyGenerateDoor(Door door)
    {
        Notification chestNotification = new Notification("GENERATE DOOR", door);
        notificationQueue.add(chestNotification);
    }
    
    @Override
    public void notifyGenerateChest(Chest chest)
    {
        Notification chestNotification = new Notification("GENERATE CHEST", chest);
        notificationQueue.add(chestNotification);
    }

    @Override
    public void notifyItemToChest(Chest chest, InventoryItem item)
    {
        Notification chestNotification = new Notification("CHEST PUTITEM", chest, item);
        notificationQueue.add(chestNotification);
    }

    public Queue<Notification> getNotificationQueue()
    {
        return notificationQueue;
    }
    
    @Override
    public void notifyTeleportPlayer(Player player, Location location)
    {
        String data = "PLAYER TELEPORT";
        notificationQueue.add(new Notification(data, player, location));
    }
    
    @Override
    public void notifyEffectPlayer(Player player, Effect effect, int duration, int power)
    {
        String data = "PLAYER EFFECT";
        List<Object> paramlist = new ArrayList<Object>();
        paramlist.add(player);
        paramlist.add(effect);
        paramlist.add(duration);
        paramlist.add(power);
        notificationQueue.add(new Notification(data, paramlist));
    }
    
    //TODO: write helpers to enchant the player
    
    @Override
    public void notifySetPlayerHealth(Player player, Integer health)
    {
        String data = "PLAYER HEALTH";
        notificationQueue.add(new Notification(data, player, health));
    }
    
    @Override
    public void notifyClearPlayerInventory(Player player)
    {
        System.out.println("Inventory clear");
        String data = "PLAYER INVENTORY_CLEAR";
        notificationQueue.add(new Notification(data, player));
    }
    
    //TODO: write helpers to add and subtract from the player inventory
    
    @Override
    public void notifyChat(String message)
    {
        String data = "CHAT BROADCAST";
        notificationQueue.add(new Notification(data, message));
    }
    
    //TODO: write helpers to chat with specific players
}
