/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.model;

/**
 *
 * USE:
 * Note that if you implement this class, that implementation _MUST_ have a
 * default constructor! If it doesn't, the item factory will not recognise it,
 * and it will not be added to the inventory.
 * 
 * This may change in the future.
 * 
 * @author salaboy
 */
public interface InventoryItem {
    public String getName();
    public void setName(String name);
    public String getType();
}
