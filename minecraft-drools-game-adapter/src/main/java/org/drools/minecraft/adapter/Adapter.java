/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.drools.core.common.DefaultFactHandle;
import org.drools.minecraft.helper.GlobalHelper;
import org.drools.minecraft.helper.GlobalHelper.Notification;
import org.drools.minecraft.model.Chest;
import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.Event;
import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.NamedLocation;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;
import org.drools.minecraft.model.Session;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 *
 * @author rs
 */
public class Adapter {

    private int throttle = 0;

    private KieSession kSession;
    private HashMap<String, Player> players;
    private HashMap<Integer, World> dimensions;
    private NotificationManager changeManager;

    private static final Adapter instance = new Adapter();
    
    //@TODO: This shouldn't exist. I need it because the world can't be
    //constructed until all setup has been finished. This means that we
    //can't construct the world until after the adapter is done being created.
    //This shouldnt matter in the long run, because the user should create
    //the world from drools, but for reference: Delete this
    //as soon as possible!
    private boolean hasConstructedWorld = false;

    //@TODO: this has to change, if we want rules accesible from different dimensions.
    //public static World world;
    //ArrayList<DroolsPlayer> players;
    /**
     * The adapter provides a bridge from Minecraft to Drools. One is created
     * automatically on game bootup
     */
    private Adapter() {
        players = new HashMap<String, Player>();
        dimensions = new HashMap<Integer, World>();
        //rooms = new ArrayList<Room>();

        bootstrapKieSession();
        
        
        GlobalHelper globalhelper = new GlobalHelper();
        changeManager = new NotificationManager(globalhelper);
        kSession.setGlobal("cmds", globalhelper);
        //we can't modify the world until after minecraft boots it up,
        //which is some time after the adapter has been constructed.
        //@TODO: relegate map creation to drools. I don't want the user
        //touching minecraft code.
        //constructWorld();

        kSession.insert(new Event("Setup"));
    }

    public static Adapter getInstance() {
        return instance;
    }

    private void bootstrapKieSession() {
        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            kSession = kContainer.newKieSession();
            
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void constructWorld(World world) {
        if (kSession != null) {
            //We must place our rooms/doors/etc relative to some point. I chose the player
            //spawn point as that point. I then lift that point some ways into the air, because
            //it kept trying to spawn underground.
            BlockPos relativeTo = world.getSpawnPoint().add(0, 40, 0);
            
            //let the rules know 1) that it can now build the world and 2) where it should build the world.
            kSession.insert(new NamedLocation(relativeTo.getX(), relativeTo.getY(), relativeTo.getZ(), "Spawnpoint"));
            kSession.insert(new Event("Startup"));
            
            //leave a flag so that the world isn't constructed repeatedly.
            hasConstructedWorld = true;
        } else {
            throw new IllegalStateException("There is no KieSession available, the rules will not work");
        }
    }
    


    /**
     * Updates a particular dimension.
     *
     * @param world
     */
    private void update(World world) {
        if(!hasConstructedWorld)
        {
            //ensure that we're not constructing rooms into blocks that will be overwritten
            //by the terrain generator
            if(world.getChunkFromBlockCoords(world.getSpawnPoint()).isPopulated()/*.isLoaded()*/)
            {
                constructWorld(world);
            }
        }
        
        for (EntityPlayer player : world.playerEntities) {
            
            //update player locations
            Player droolsPlayer = players.get(player.getName());
            Location playerLoc = droolsPlayer.getLocation();
            playerLoc.setX(player.getPosition().getX());
            playerLoc.setY(player.getPosition().getY());
            playerLoc.setZ(player.getPosition().getZ());
            
            //if the inventory has been changed, rebuild it.
            if (droolsPlayer.getInventoryDirty()) {
                rebuildInventory(player);
            }
            droolsPlayer.setInventoryDirty(false);

            //if the player's location has caused him to change rooms,
            //update this within the model.
            droolsPlayer.getRoomsIn().clear();
            for (FactHandle handle : kSession.getFactHandles(new ClassObjectFilter(Room.class))) {
                Room room = (Room) ((DefaultFactHandle) handle).getObject(); 
                
                if (playerWithinRoom(droolsPlayer, room)) {
                    room.addPlayer(player.getName());
                }else
                {
                    room.removePlayer(player.getName());
                }
                kSession.update(kSession.getFactHandle(room), room);
            }
            
            if(player.inventory.inventoryChanged)
            {
                rebuildInventory(player);
                player.inventory.inventoryChanged = false;
            }
            
            kSession.update(kSession.getFactHandle(droolsPlayer), droolsPlayer);
        }
        
        //cause rules to fire
        kSession.fireAllRules();
        
        //react to changes made by drools by retrieving the helper
        GlobalHelper helper = (GlobalHelper)kSession.getGlobal("cmds");
        changeManager.update(world);
    }

    /**
     * Execute any updates that occur when the game ticks.
     *
     * @param event
     */
    @SubscribeEvent
    public void onServerTick(TickEvent.WorldTickEvent event) {
        if (!event.world.isRemote) {
            throttle++;

            if (throttle % 50 == 0) {
                //for simplicity's sake, this locks the adapter into only working
                //in the default dimension. Rules will not work in the nether or end.
                //We should change this at some point.
                if (event.world.provider.getDimensionId() == 0) {
                    if (!dimensions.containsKey(event.world.provider.getDimensionId())) {
                        dimensions.put(event.world.provider.getDimensionId(), event.world);
                    }
                    update(event.world);
                }
            }
        }
    }

    /**
     * Set up player session, inventory, etc.
     *
     * @param event
     */
    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event) {
        if (!event.world.isRemote) {
            if (event.entity instanceof EntityPlayer) {
                Player player = new Player();
                players.put(event.entity.getName(), player);
                player.setInventoryDirty(true);
                player.setName(event.entity.getName());

                kSession.insert(new Session(player));
                kSession.insert(player);
                kSession.insert(player.getInventory());
            }
        }
    }
    
    /**
     * When a player exits, remove them from the rules.
     *
     * @param event
     */
    @SubscribeEvent
    public void onPlayerExit(EntityJoinWorldEvent event) {
        if (!event.world.isRemote) {
            if (event.entity instanceof EntityPlayer) {
                Player player = new Player();
                
                Player droolsPlayer = players.get(player.getName());
                players.remove(player.getName());
                
                //Clear the removed player out of any rooms.
                for (FactHandle handle : kSession.getFactHandles(new ClassObjectFilter(Room.class))) {
                    Room room = (Room) ((DefaultFactHandle) handle).getObject(); 
                    if (playerWithinRoom(droolsPlayer, room)) {
                        room.removePlayer(player.getName());
                        kSession.update(kSession.getFactHandle(room), room);
                    }
                }
                if(kSession.getFactHandle(droolsPlayer) != null)
                {
                    kSession.retract(kSession.getFactHandle(droolsPlayer));
                }
            
            }
            
            
        }
        
    }

    /**
     * Whenever the player's inventory changes, rebuild the
     * model on the minecraft side. There's just too much data
     * to track to keep a synchronised model.
     *
     * @param entity
     */
    private void rebuildInventory(EntityPlayer entity) {
        
        Player player = players.get(entity.getName());
        player.getInventory().clear();
        
        for (int i = 0; i < entity.inventory.mainInventory.length; i++) {
            ItemStack stack = entity.inventory.mainInventory[i];
            if (stack != null) {
                try {
                    player.getInventory().add(ItemsFactory.newItem(stack.getUnlocalizedName(), stack.getDisplayName()));
                } catch (Exception ex) {
                    Logger.getLogger(Adapter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        for (int i = 0; i < entity.inventory.armorInventory.length; i++) {
            ItemStack stack = entity.inventory.armorInventory[i];
            if (stack != null) {
                try {
                    player.getInventory().add(ItemsFactory.newItem(stack.getUnlocalizedName(), stack.getDisplayName()));
                } catch (Exception ex) {
                    Logger.getLogger(Adapter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        kSession.update(kSession.getFactHandle(player.getInventory()), player.getInventory());
    }

    /**
     * helper method. Determines if a player is mathematically
     * within a room.
     * @TODO: externalise.
     * @param player
     * @param room
     * @return 
     */
    public boolean playerWithinRoom(Player player, Room room) {
        Location playerLoc = player.getLocation();
        Location roomLowerLoc = room.getLowerBound();
        Location roomUpperLoc = room.getUpperBound();
        boolean xWithin = within(playerLoc.getX(), roomLowerLoc.getX(), roomUpperLoc.getX());
        boolean yWithin = within(playerLoc.getY(), roomLowerLoc.getY(), roomUpperLoc.getY());
        boolean zWithin = within(playerLoc.getZ(), roomLowerLoc.getZ(), roomUpperLoc.getZ());
        return xWithin && yWithin && zWithin;
    }

    /**
     * helper method. Determines if a number is within bounds.
     * @TODO: externalise.
     * @param player
     * @param room
     * @return 
     */
    public boolean within(int number, int first, int second) {
        int min = Math.min(first, second);
        int max = Math.max(first, second);
        return number >= min && number <= max;
    }

    public HashMap<Integer, World> getDimensions() {
        return dimensions;
    }

    public void setDimensions(HashMap<Integer, World> dimensions) {
        this.dimensions = dimensions;
    }
    
    

}
