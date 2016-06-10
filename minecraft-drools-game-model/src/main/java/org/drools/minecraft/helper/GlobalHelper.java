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
import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.Player;

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
    
    
}
