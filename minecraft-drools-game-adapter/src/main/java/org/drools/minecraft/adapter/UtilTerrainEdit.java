/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.Location;
import org.drools.minecraft.model.Room;

/**
 *
 * @author Samuel
 */
public class UtilTerrainEdit
{
    //
    //
    //
    // I know that these helpers are massively ugly and probably in the wrong place. Don't worry about them quite yet,
    // I'll move them when we get drools/MC communication closer to finished.
    //
    //
    //
    
    /**
     * Constructs a room within minecraft.
     * @param worldin
     * @param room 
     */
    public static void constructRoom(World worldin, Room room)
    {
        Location lower = room.getLowerBound();
        Location upper = room.getUpperBound();
        
        //clear out any existing terrains
        for(int x = lower.getX() + 1; x < upper.getX(); x++)
        {
            for(int y = lower.getY() + 1; y < upper.getY(); y++)
            {
                for(int z = lower.getZ() + 1; z < upper.getZ(); z++)
                {
                    worldin.setBlockState(new BlockPos(x, y, z), Blocks.air.getDefaultState());
                }
            }
        }
        
        //make the cieling
        for(int x = lower.getX(); x <= upper.getX(); x++)
        {
            for (int z = lower.getZ(); z <= upper.getZ(); z++)
            {
                worldin.setBlockState(new BlockPos(x, upper.getY(), z), Blocks.sea_lantern.getDefaultState());
            }
        }
        //make the floor
        for(int x = lower.getX(); x <= upper.getX(); x++)
        {
            for (int z = lower.getZ(); z <= upper.getZ(); z++)
            {
                worldin.setBlockState(new BlockPos(x, lower.getY(), z), Blocks.stone.getDefaultState());
            }
        }
        
        //make the front wall
        for(int x = lower.getX(); x <= upper.getX(); x++)
        {
            for (int y = lower.getY(); y <= upper.getY(); y++)
            {
                worldin.setBlockState(new BlockPos(x, y, upper.getZ()), Blocks.stone.getDefaultState());
            }
        }
        //make the back wall
        for(int x = lower.getX(); x <= upper.getX(); x++)
        {
            for (int y = lower.getY(); y <= upper.getY(); y++)
            {
                worldin.setBlockState(new BlockPos(x, y, lower.getZ()), Blocks.stone.getDefaultState());
            }
        }
        
        //make the left wall
        for(int y = lower.getY(); y <= upper.getY(); y++)
        {
            for (int z = lower.getZ(); z <= upper.getZ(); z++)
            {
                worldin.setBlockState(new BlockPos(upper.getX(), y, z), Blocks.stone.getDefaultState());
            }
        }
        //make the right wall
        for(int y = lower.getY(); y <= upper.getY(); y++)
        {
            for (int z = lower.getZ(); z <= upper.getZ(); z++)
            {
                worldin.setBlockState(new BlockPos(lower.getX(), y, z), Blocks.stone.getDefaultState());
            }
        }
    }
    
    /**
     * Constructs a door within the minecraft world
     * @param worldin
     * @param room 
     */
    public static void constructDoor(World worldin, Door door)
    {
        Location lower = door.getLowerBound();
        Location upper = door.getUpperBound();
        
        for(int x = lower.getX(); x <= upper.getX(); x++)
        {
            for(int y = lower.getY(); y <= upper.getY(); y++)
            {
                for(int z = lower.getZ(); z <= upper.getZ(); z++)
                {
                    worldin.setBlockState(new BlockPos(x, y, z), Blocks.planks.getDefaultState());
                }
            }
        }
    }
    
    public static void placeKeyChest(World world, BlockPos location, String keyname)
    {
        world.setBlockState(location, net.minecraft.init.Blocks.chest.getDefaultState(), 2);
        TileEntity chestEntity = world.getTileEntity(location);

        if (chestEntity instanceof TileEntityChest) {
                    ((TileEntityChest) chestEntity).setInventorySlotContents(0, new ItemStack(GameRegistry.findItem("examplemod", "key")).setStackDisplayName(keyname));
        }
    }
}
