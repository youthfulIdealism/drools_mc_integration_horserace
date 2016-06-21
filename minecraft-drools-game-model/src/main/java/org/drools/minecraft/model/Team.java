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
 * @author Samuel
 */
public class Team extends ArrayList<Player>
{
    String id;
    public Team(String name)
    {
        this.id = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    
    
}
