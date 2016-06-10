/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.helper;

import org.drools.minecraft.model.Door;

/**
 *
 * @author salaboy
 */
public interface CommandHelper {
    
    public void notifyOpenDoor(Door door);
    
    public void notifyCloseDoor(Door door);
    
}
