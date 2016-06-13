/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import java.util.List;
import java.util.Queue;
import net.minecraft.world.World;
import org.drools.minecraft.helper.GlobalHelper;
import org.drools.minecraft.model.Chest;
import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.InventoryItem;
import org.drools.minecraft.model.Item;
import org.drools.minecraft.model.Room;

/**
 *
 * @author Samuel
 */
public class NotificationManager
{
    GlobalHelper helper;
    
    public NotificationManager(GlobalHelper helper)
    {
        this.helper = helper;
         
    }
    
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
            }
        }
    }
    
    /**
     * Parses a notification and changes door blocks accordingly.
     * 
     * @param parsedIndicator
     * @param ParamList
     * @param world 
     */
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
    
}
