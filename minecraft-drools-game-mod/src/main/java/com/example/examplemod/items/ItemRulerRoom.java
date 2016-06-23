/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.examplemod.items;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 *
 * @author Samuel
 */
public class ItemRulerRoom extends Item {

    public static final String outString = "..\\";
    private BlockPos workingPos;
    
    public ItemRulerRoom() {
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
        workingPos = null;
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(worldIn.isRemote)
        {
            if(workingPos != null)
            {
                System.out.println("Ruler writing: " + workingPos + " to: " + pos);
                BufferedWriter roomWriter = null;
                try
                {
                    roomWriter = new BufferedWriter(new FileWriter(outString + "roomdefs.txt", true));
                    roomWriter.write("Room " + stack.getDisplayName().toLowerCase().replaceAll(" ", "") + " = new Room(" + workingPos.getX() + ", " + workingPos.getY() + ", " + workingPos.getZ() + ", " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ", \"" + stack.getDisplayName() + "\");");
                    roomWriter.write("\n");
                    roomWriter.write("insert(" + stack.getDisplayName().toLowerCase().replaceAll(" ", "") + ");");
                    roomWriter.write("\n");
                    roomWriter.flush();
                    playerIn.addChatComponentMessage(new ChatComponentText("Defined room " + stack.getDisplayName()));
                } catch (IOException ex)
                {
                    Logger.getLogger(ItemRulerRoom.class.getName()).log(Level.SEVERE, null, ex);
                } finally
                {
                    try
                    {
                        roomWriter.close();
                    } catch (IOException ex){}
                }
                workingPos = null;
            }else
            {
                workingPos = pos;
                //for(EntityPlayer player : playerIn)
                //{
                    playerIn.addChatComponentMessage(new ChatComponentText("Started defining room " + stack.getDisplayName()));
                //}
            }
        }
        
        
        return false;
    }
    
    
}
