/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salaboy
 */
public class Chest implements IItem {

    private String name;
    private List<IItem> content;
    private Location location;

    public Chest(String name) {
        this.name = name;
    }

    public Chest(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IItem> getContent() {
        return content;
    }

    public void setContent(List<IItem> content) {
        this.content = content;
    }

    public void addItem(IItem item) {
        if (this.content == null) {
            this.content = new ArrayList<>();
        }
        this.content.add(item);
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String getType() {
        return "item.chest";
    }

    @Override
    public String toString() {
        return "Chest{" + "name=" + name + ", content=" + content + '}';
    }

}
