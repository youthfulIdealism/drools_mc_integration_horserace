/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.model;

/**
 *
 * @author salaboy
 */
public class Key implements InventoryItem {

    private String name;

    public Key(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "item.key";
    }

    @Override
    public String toString() {
        return "Key{" + "name=" + name + '}';
    }

}
