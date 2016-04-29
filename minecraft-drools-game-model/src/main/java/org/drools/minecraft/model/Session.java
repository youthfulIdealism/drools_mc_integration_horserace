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
public class Session
{
    DroolsPlayer player;
    public Session(DroolsPlayer player)
    {
        this.player = player;
    }

    public DroolsPlayer getPlayer() {
        return player;
    }

    public void setPlayer(DroolsPlayer player) {
        this.player = player;
    }
    
}
