/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.rules;

import org.drools.minecraft.model.Door;
import org.drools.minecraft.helper.CommandHelper;

/**
 *
 * @author salaboy
 */
public class MockCommandHelper implements CommandHelper {

    @Override
    public void openDoor(Door door) {
        System.out.println("Opening Door: " + door);
    }

    @Override
    public void closeDoor(Door door) {
        System.out.println("Closing Door: " + door);
    }

}
