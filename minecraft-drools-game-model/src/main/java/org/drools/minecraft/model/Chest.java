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

    public Chest(String name) {
        this.name = name;
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

}
