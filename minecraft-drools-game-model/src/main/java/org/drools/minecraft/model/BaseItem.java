/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.model;

/**
 *
 * @author Samuel
 */
public class BaseItem implements InventoryItem
{
    private String name;
    private String type;
    
    
    
    public BaseItem(String name, String type)
    {
        this.name = name;
        this.type = type;
    }
    
    
    @Override
    public String getName()
    {
        return name;
    }
    
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getType()
    {
        return type;
    }

    
    
}
