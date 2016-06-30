/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.drools.core.common.DefaultFactHandle;
import org.drools.minecraft.helper.GlobalHelper;
import org.drools.minecraft.model.Event;
import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.Mob;
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
    
    /**
     * The adapter provides a bridge from Minecraft to Drools. One is created
     * automatically on game bootup
     */
    private Adapter() {
        dimensions = new HashMap<Integer, World>();

        bootstrapKieSession();
        
        
        GlobalHelper globalhelper = new GlobalHelper();
        changeManager = new NotificationManager(globalhelper);
        kSession.setGlobal("cmds", globalhelper);

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
            //kSession = kContainer.newKieSession();
            kSession = kContainer.newKieBase("capturetheflagKBase", null).newKieSession();
            
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    
    /**
     * Updates a particular dimension.
     *
     * @param world
     */
    private void update(World world) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        if(!hasConstructedWorld)
        {
            //ensure that we're not constructing rooms into blocks that will be overwritten
            //by the terrain generator
            if(world.getChunkFromBlockCoords(world.getSpawnPoint()).isPopulated())
            {
                constructWorld(world);
            }
        }
        
        for(FactHandle playerHandle : kSession.getFactHandles(new ClassObjectFilter(Player.class))) {
            Player droolsPlayer = (Player) ((DefaultFactHandle) playerHandle).getObject(); 
            
            //update player locations
            EntityPlayer player = world.getPlayerEntityByName(droolsPlayer.getName());
            
            Location playerLoc = droolsPlayer.getLocation();
            playerLoc.setX(player.getPosition().getX());
            playerLoc.setY(player.getPosition().getY());
            playerLoc.setZ(player.getPosition().getZ());
            
            droolsPlayer.setExperience(player.experienceLevel);
            droolsPlayer.setHealth(player.getHealth());
            droolsPlayer.setHunger(20 - player.getFoodStats().getFoodLevel());
            droolsPlayer.setIsBurning(player.isBurning());
            droolsPlayer.setIsDead(player.isDead);
            
            //if the inventory has been changed, rebuild it.
            if (droolsPlayer.getInventoryDirty() || player.inventory.inventoryChanged) {
                //TODO: this doesn't always work! Find a reliable way to determine when
                //the inventory has changed.
                rebuildInventory(player);
                player.inventory.inventoryChanged = false;
            }
            droolsPlayer.setInventoryDirty(false);

            //if the player's location has caused him to change rooms,
            //update this within the model.
            droolsPlayer.getRoomsIn().clear();
            for (FactHandle handle : kSession.getFactHandles(new ClassObjectFilter(Room.class))) {
                Room room = (Room) ((DefaultFactHandle) handle).getObject(); 
                
                if (UtilMathHelper.playerWithinRoom(droolsPlayer, room)) {
                    room.addPlayer(player.getName());
                }else
                {
                    room.removePlayer(player.getName());
                }
            }
            
            kSession.update(kSession.getFactHandle(droolsPlayer), droolsPlayer);
        }
        
        
        for (FactHandle handle : kSession.getFactHandles(new ClassObjectFilter(Room.class))) {
                Room room = (Room) ((DefaultFactHandle) handle).getObject(); 
                
                Location lower = room.getLowerBound();
                Location upper = room.getUpperBound();
                List<EntityCreature> considered = world.getEntitiesWithinAABB(EntityCreature.class, new AxisAlignedBB(lower.getX(), lower.getY(), lower.getZ(), upper.getX(), upper.getY(), upper.getZ()));
                
                List<String> untouchedMobs = new ArrayList<String>(room.getMobsInRoom().keySet().size());
                for(String id : room.getMobsInRoom().keySet())
                {
                    untouchedMobs.add(id);
                }
                
                
                for(EntityCreature creature : considered)
                {
                    String id = creature.getUniqueID().toString();
                    if(!room.getMobsInRoom().containsKey(id))
                    {
                        boolean found = false;
                        Mob mob = null;
                        
                        for (FactHandle mobHandle : kSession.getFactHandles(new ClassObjectFilter(Mob.class))) {
                            Mob potentialMob = (Mob) ((DefaultFactHandle) mobHandle).getObject(); 
                            if(potentialMob.getId().equals(id))
                            {
                                mob = potentialMob;
                                found = true;
                            }
                        }
                        
                        if(!found)
                        {
                            mob = new Mob(MobFactory.newMobType(creature), id, new Location((int)creature.posX, (int)creature.posY, (int)creature.posZ));
                            kSession.insert(mob);
                        }
                        mob.setRoomcount(mob.getRoomcount() + 1);
                        room.getMobsInRoom().put(id, mob);
                        
                    }else
                    {
                        Mob updatingMob = room.getMobsInRoom().get(id);
                        updatingMob.setLocation(new Location((int)creature.posX, (int)creature.posY, (int)creature.posZ));
                        
                        untouchedMobs.remove(id);
                        kSession.update(kSession.getFactHandle(updatingMob), updatingMob);
                    }
                }
                
                for(String deletedMobID : untouchedMobs)
                {
                     Mob updatingMob = room.getMobsInRoom().get(deletedMobID);
                     room.getMobsInRoom().remove(deletedMobID);
                     updatingMob.setRoomcount(updatingMob.getRoomcount() - 1);
                     
                     if(updatingMob.getRoomcount() <= 0)
                     {
                         kSession.retract(kSession.getFactHandle(updatingMob));
                     }
                }
                
                kSession.update(kSession.getFactHandle(room), room);
        }
        
        //cause rules to fire
        kSession.fireAllRules();
        
        //react to changes made by drools by retrieving the helper
        GlobalHelper helper = (GlobalHelper)kSession.getGlobal("cmds");
        changeManager.update(world);
    }
    
    private void constructWorld(World world) {
        if (kSession != null) {
            //We must place our rooms/doors/etc relative to some point. I chose the player
            //spawn point as that point. I then lift that point some ways into the air, because
            //it kept trying to spawn underground.
            BlockPos relativeTo = world.getSpawnPoint().add(0, 30, 0);
            
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
     * Execute any updates that occur when the game ticks.
     *
     * @param event
     */
    @SubscribeEvent
    public void onServerTick(TickEvent.WorldTickEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (!event.world.isRemote) {
            if(event.phase == TickEvent.WorldTickEvent.Phase.START)
            {
                return;
            }
            throttle++;

            if (throttle % 20 == 0) {
                //for simplicity's sake, this locks the adapter into only working
                //in the default dimension. Rules will not work in the nether or end.
                //We should change this at some point.
                if (event.world.provider.getDimension() == 0) {
                    if (!dimensions.containsKey(event.world.provider.getDimension())) {
                        dimensions.put(event.world.provider.getDimension(), event.world);
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
        if (!event.getWorld().isRemote) {
            if (event.getEntity() instanceof EntityPlayer) {
                Player player = new Player();
                String playername = event.getEntity().getDisplayName().getUnformattedText();
                player.setInventoryDirty(true);
                player.setName(playername);

                kSession.insert(new Session(player));
                kSession.insert(player);
                
                //TODO: Find out if we need to insert the player's inventory.
                //My gut says no, but I guess it really depends on the user
                //could use it.
                kSession.insert(player.getInventory());
            }
        }
    }
    
    /**
     * When the player dies, remove him from any occupied rooms.
     *
     * @param event
     */
    @SubscribeEvent
    public void onPlayerDie(LivingDeathEvent event) {
        if (!event.getEntity().worldObj.isRemote) {
            if (event.getEntity() instanceof EntityPlayer) {
                String playername = event.getEntity().getDisplayName().getUnformattedText();

                //Clear the removed player out of any rooms.
                for (FactHandle handle : kSession.getFactHandles(new ClassObjectFilter(Room.class))) {
                    Room room = (Room) ((DefaultFactHandle) handle).getObject(); 
                    if (room.getPlayersInRoom().contains(playername)) {
                        room.removePlayer(playername);
                        kSession.update(kSession.getFactHandle(room), room);
                    }
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
        
        //find the associated Player in drools
        Player player = null;
        for (FactHandle handle : kSession.getFactHandles(new ClassObjectFilter(Player.class))) {
            Player testplayer = (Player) ((DefaultFactHandle) handle).getObject(); 
            if(testplayer.getName().equals(entity.getName()))
            {
                player = testplayer;
                break;
            }
        }
        
        //if we've found the player,
        if(player != null)
        {
            //clear the inventory out,
            player.getInventory().clear();
            
            //and add whatever is in his main,...
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
            
            //...and armor inventories into the drools inventory
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
    }

    

    public HashMap<Integer, World> getDimensions() {
        return dimensions;
    }

    public void setDimensions(HashMap<Integer, World> dimensions) {
        this.dimensions = dimensions;
    }
}
