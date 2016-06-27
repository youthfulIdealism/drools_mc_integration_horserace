/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.helper;

import org.drools.minecraft.model.Chest;
import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.InventoryItem;
import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.Mob.MobTypes;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;

/**
 *
 * @author salaboy
 */
public interface CommandHelper {
    
    public enum Effect {
        SPEED(1), SLOWNESS(2), HASTE(3),
        FATIGUE(4), STRENGTH(5), HEALTH(6),
        DAMAGE(7), JUMP_BOOST(8), NAUSEA(9),
        REGENERATION(10), RESISTANCE(11), FIRE_RESISTANCE(12),
        WATERB_REATHING(13), INVISIBILITY(14), BLINDNESS(15),
        NIGHT_VISION(16), HUNGER(17), WEAKNESS(18),
        POISON(19), WITHER(20), HEALTH_BOOST(21),
        ABSORPTION(22), SATURATION(23);
    
        public final int effectid;
        Effect(int id)
        {
            effectid = id;
        }
    }
    
    
    public void notifyOpenDoor(Door door);
    
    public void notifyCloseDoor(Door door);
    
    public void notifyTeleportPlayer(Player player, Location location);
    
    public void notifyGenerateDoor(Door door);
    
    public void notifyGenerateRoom(Room room);
    
    public void notifyGenerateChest(Chest chest);
    
    public void notifyItemToChest(Chest chest, InventoryItem item);
    
    public void notifySetPlayerHealth(Player player, Integer health);
    
    public void notifyClearPlayerInventory(Player player);
    
    //TODO: Find out if we want to represent effects in the model.
    public void notifyEffectPlayer(Player player, Effect effect, int duration, int power);
    
    public void notifyChat(String contents);
    
    public void notifyChat(String contents, Player player);
    
    public void notifyMobSpawn(MobTypes type, Location location);
}
