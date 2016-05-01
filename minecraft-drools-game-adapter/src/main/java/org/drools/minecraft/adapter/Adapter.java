/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.drools.minecraft.model.DroolsPlayer;
import org.drools.minecraft.model.Inventory;
import org.drools.minecraft.model.Item;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;
import org.drools.minecraft.model.Session;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 *
 * @author rs
 */
public class Adapter
{

    int throttle = 0;

    public static KieSession kSession;
    public static HashMap<String, DroolsPlayer> players;
    public static HashMap<Integer, World> dimensions;
    public static ArrayList<Room> rooms;
    Adapter adapter;

    //TODO: this has to change, if we want rules accesible from different dimensions.
    //public static World world;
    //ArrayList<DroolsPlayer> players;
    /**
     * The adapter provides a bridge from Minecraft to Drools. One is created automatically on game
     * bootup, so don't instantiate this class!
     */
    public Adapter()
    {
        if (adapter != null)
        {
            System.err.println("Hey, bub, you tried to build two adapters. I don't like it when you do that. In retribution, I think I'll leak memory unboundedly for a while.");
        }
        adapter = this;
        players = new HashMap<String, DroolsPlayer>();
        dimensions = new HashMap<Integer, World>();
        rooms = new ArrayList<Room>();

        System.out.println("Adapter built!");

        try
        {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            kSession = kContainer.newKieSession("ksession-rules");

            kSession.fireAllRules();
        } catch (Throwable t)
        {
            t.printStackTrace();
        }
        //kSession.insert(this);

        Room defaultRoom = new Room(-85, 76, 438, -83, 79, 438, "LighthouseDoor");
        rooms.add(defaultRoom);
        kSession.insert(defaultRoom);
    }

    /**
     * Updates a particular dimension.
     * @param world 
     */
    public void update(World world)
    {
        for (EntityPlayer player : world.playerEntities)
        {
            DroolsPlayer droolsPlayer = players.get(player.getName());
            droolsPlayer.setXloc(player.getPosition().getX());
            droolsPlayer.setYloc(player.getPosition().getY());
            droolsPlayer.setZloc(player.getPosition().getZ());
            if(droolsPlayer.getInventory().isDirty())
            {
                rebuildInventory(player);
            }
            droolsPlayer.getInventory().setDirty(false);
            
            droolsPlayer.getRoomsIn().clear();
            for(Room room : rooms)
            {
                if(playerWithinRoom(droolsPlayer, room))
                {
                    droolsPlayer.getRoomsIn().add(room);
                    kSession.update(kSession.getFactHandle(room), room);
                }
            }
            kSession.update(kSession.getFactHandle(droolsPlayer), droolsPlayer);
        }
        kSession.fireAllRules();
    }

    /**
     * Execute any updates that occur when the game ticks.
     * @param event 
     */
    @SubscribeEvent
    public void onServerTick(TickEvent.WorldTickEvent event)
    {
        if (!event.world.isRemote)
        {
            throttle++;
            if (throttle % 16 == 0)
            {
                //for simplicity's sake, this locks the adapter into only working
                //in the default dimension. Rules will not work in the nether or end.
                //We should change this at some point.
                if (event.world.provider.getDimensionId() == 0)
                {
                    if(!dimensions.containsKey(event.world.provider.getDimensionId()))
                    {
                        dimensions.put(event.world.provider.getDimensionId(), event.world);
                    }
                    adapter.update(event.world);
                }
            }
        }
    }

    /**
     * Set up player session, inventory, etc.
     * @param event 
     */
    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event)
    {
        if (!event.world.isRemote)
        {
            if (event.entity instanceof EntityPlayer)
            {
                DroolsPlayer player = new DroolsPlayer();
                players.put(event.entity.getName(), player);
                player.getInventory().setDirty(true);

                kSession.insert(new Session(player));
                kSession.insert(player);
                kSession.insert(player.getInventory());
            }
        }
    }
    
    /**
     * Whenever the player's inventory changes, we need to entirely
     * rebuild the model on the minecraft side. There's just too much
     * data to track to keep a synchronised model.
     * @param entity 
     */
    public void rebuildInventory(EntityPlayer entity)
    {
        DroolsPlayer player = players.get(entity.getName());
        Inventory inventory = player.getInventory();
        inventory.getItems().clear();
        for (int i = 0; i < entity.inventory.mainInventory.length; i++)
        {
            ItemStack stack = entity.inventory.mainInventory[i];
            if (stack != null)
            {
                inventory.getItems().add(new Item(stack.getUnlocalizedName(), stack.stackSize));
            }
        }
        for (int i = 0; i < entity.inventory.armorInventory.length; i++)
        {
            ItemStack stack = entity.inventory.armorInventory[i];
            if (stack != null)
            {
                inventory.getItems().add(new Item(stack.getUnlocalizedName(), stack.stackSize));
            }
        }
        kSession.update(kSession.getFactHandle(player.getInventory()), player.getInventory());
    }
    
    /**
     * Find out if an inventory needs rebuilding.
     * @param event 
     */
    @SubscribeEvent
    public void addInventoryItem(EntityItemPickupEvent event)
    {
        if (!event.entityLiving.worldObj.isRemote)
        {
            if (event.entityPlayer != null)
            {
                DroolsPlayer player = players.get(event.entityPlayer.getName());
                player.getInventory().setDirty(true);
            }
        }
    }

    /**
     * Find out if an inventory needs rebuilding.
     * @param event 
     */
    @SubscribeEvent
    public void dropInventoryItem(ItemTossEvent event)
    {
        if (!event.entity.worldObj.isRemote)
        {
            if (event.player != null)
            {
                DroolsPlayer player = players.get(event.player.getName());
                player.getInventory().setDirty(true);
            }
        }
    }
    
    public boolean playerWithinRoom(DroolsPlayer player, Room room)
    {
        boolean xWithin = within(player.getXloc(), room.getX(), room.getFx());
        boolean yWithin = within(player.getYloc(), room.getY(), room.getFy());
        boolean zWithin = within(player.getZloc(), room.getZ(), room.getFz());
        return xWithin && yWithin && zWithin;
    }
    
    public boolean within(int number, int first, int second)
    {
        int min = Math.min(first, second);
        int max = Math.max(first, second);
        return number >= min && number <= max;
    }

    public static HashMap<Integer, World> getDimensions()
    {
        return dimensions;
    }

    public static void setDimensions(HashMap<Integer, World> dimensions)
    {
        Adapter.dimensions = dimensions;
    }
    
    
}
