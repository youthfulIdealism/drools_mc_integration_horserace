package org.drools.minecraft.util;

import org.drools.minecraft.model.DroolsPlayer;
import java.util.EnumSet;
import net.minecraft.block.Block;

import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.drools.minecraft.adapter.Adapter;
import org.drools.minecraft.model.Item;
import org.drools.minecraft.model.Room;

/**
 *
 * Stub class. Used by drools to request actions from minecraft.
 *
 * @author Samuel
 *
 */
public class DroolsTestInterface
{
    public static boolean playerHasItem(DroolsPlayer player, String item)
    {
        for(Item inventoryItem : player.getInventory().getItems())
        {
            if(inventoryItem.getType().equals(item))
            {
                return true;
            }
        }
        return false;
    }
    
    public static boolean playerInRoom(DroolsPlayer player, Room room)
    {
        return player.getRoomsIn().contains(room);
    }
}
