///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.drools.minecraft.adapter;
//
//import net.minecraft.init.Blocks;
////import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.tileentity.TileEntityChest;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.common.registry.GameRegistry;
//import org.drools.game.capture.flag.model.Chest;
//import org.drools.game.capture.flag.model.Location;
//import org.drools.game.capture.flag.model.Zone;
//import org.drools.game.model.api.Item;
//
//
///**
// *
// * @author Samuel
// */
//public class UtilTerrainEdit
//{
//    //
//    //
//    //
//    // I know that these helpers are massively ugly and probably in the wrong place. Don't worry about them quite yet,
//    // I'll move them when we get drools/MC communication closer to finished.
//    //
//    //
//    //
//    
//    /**
//     * Constructs a room within minecraft.
//     * @param worldin
//     * @param room 
//     */
//    public static void constructRoom(World worldin, Zone room)
//    {
//        Location lower = room.getLowerBound();
//        Location upper = room.getUpperBound();
//        
//        //clear out any existing terrains
//        for(int x = lower.getX() + 1; x < upper.getX(); x++)
//        {
//            for(int y = lower.getY() + 1; y < upper.getY(); y++)
//            {
//                for(int z = lower.getZ() + 1; z < upper.getZ(); z++)
//                {
//                    worldin.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
//                }
//            }
//        }
//        
//        //make the cieling
//        for(int x = lower.getX(); x <= upper.getX(); x++)
//        {
//            for (int z = lower.getZ(); z <= upper.getZ(); z++)
//            {
//                worldin.setBlockState(new BlockPos(x, upper.getY(), z), Blocks.SEA_LANTERN.getDefaultState());
//            }
//        }
//        //make the floor
//        for(int x = lower.getX(); x <= upper.getX(); x++)
//        {
//            for (int z = lower.getZ(); z <= upper.getZ(); z++)
//            {
//                worldin.setBlockState(new BlockPos(x, lower.getY(), z), Blocks.STONE.getDefaultState());
//            }
//        }
//        
//        //make the front wall
//        for(int x = lower.getX(); x <= upper.getX(); x++)
//        {
//            for (int y = lower.getY(); y <= upper.getY(); y++)
//            {
//                worldin.setBlockState(new BlockPos(x, y, upper.getZ()), Blocks.STONE.getDefaultState());
//            }
//        }
//        //make the back wall
//        for(int x = lower.getX(); x <= upper.getX(); x++)
//        {
//            for (int y = lower.getY(); y <= upper.getY(); y++)
//            {
//                worldin.setBlockState(new BlockPos(x, y, lower.getZ()), Blocks.STONE.getDefaultState());
//            }
//        }
//        
//        //make the left wall
//        for(int y = lower.getY(); y <= upper.getY(); y++)
//        {
//            for (int z = lower.getZ(); z <= upper.getZ(); z++)
//            {
//                worldin.setBlockState(new BlockPos(upper.getX(), y, z), Blocks.STONE.getDefaultState());
//            }
//        }
//        //make the right wall
//        for(int y = lower.getY(); y <= upper.getY(); y++)
//        {
//            for (int z = lower.getZ(); z <= upper.getZ(); z++)
//            {
//                worldin.setBlockState(new BlockPos(lower.getX(), y, z), Blocks.STONE.getDefaultState());
//            }
//        }
//    }
//    
//    
//    
//    /**
//     * Places a chest in the world given a location.
//     * @param world
//     * @param chest 
//     */
//    public static void placeChest(World world, Chest chest)
//    {
//        Location location = chest.getLocation();
//        BlockPos pos = new BlockPos(location.getX(), location.getY(), location.getZ());
//        world.setBlockState(pos, net.minecraft.init.Blocks.CHEST.getDefaultState(), 2);
//    }
//    
//    /**
//     * Places a chest in the world at a blockpos.
//     * @param world
//     * @param location
//     * @param keyname 
//     */
//    public static void placeChest(World world, BlockPos location)
//    {
//        world.setBlockState(location, net.minecraft.init.Blocks.CHEST.getDefaultState(), 2);
//    }
//    
//    /**
//     * Adds an item to an existing chest.
//     * //TODO: write a version that can take a location instead, and add a corresponding
//     * //notification.
//     * @param world
//     * @param chest
//     * @param item 
//     */
//    public static void addChestItem(World world,  Chest chest, Item item)
//    {
//        //find the chest in minecraft
//        Location location = chest.getLocation();
//        BlockPos pos = new BlockPos(location.getX(), location.getY(), location.getZ());
//        world.setBlockState(pos, net.minecraft.init.Blocks.CHEST.getDefaultState(), 2);
//        TileEntity chestEntity = world.getTileEntity(pos);
//        
//        //generate the item and place it in the chest
//        net.minecraft.item.Item minecraftItem = GameRegistry.findItem("examplemod", item.getType().replace("item.", "").replace("block.", ""));
//        if(minecraftItem == null)
//        {
//            minecraftItem = GameRegistry.findItem("minecraft", item.getType().replace("item.", "").replace("block.", ""));
//        }
//        if(minecraftItem == null)
//        {
//            //TODO: find a way to handle any additional mods
//        }
//        if(minecraftItem != null)
//        {
//            ItemStack stack = new ItemStack(minecraftItem);
//            if (chestEntity instanceof TileEntityChest) {
//                for(int i = 0; i < ((TileEntityChest) chestEntity).getSizeInventory(); i++)
//                {
//                    if(((TileEntityChest) chestEntity).getStackInSlot(i) == null)
//                    {
//                        ((TileEntityChest) chestEntity).setInventorySlotContents(i, stack.setStackDisplayName(item.getName()));
//                        break;
//                    }
//                }
//            }
//            else
//            {
//                System.out.println("Error placing item in chest: chest not found in world.");
//            }
//        }else
//        {
//            System.out.println("Error placing item in chest: item " + item.getType() + " not found.");
//        }
//        
//    }
//}
