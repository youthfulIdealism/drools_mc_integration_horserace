package org.drools.minecraft.model.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.Key;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;
import org.drools.minecraft.model.Chest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salaboy
 */
public class ModelJUnitTest {

    public ModelJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    /*
     We have two rooms interconected by a Door and an exit door on Room B
    that takes outside.
    In room A we have a chest with a Key for "to Room B" door
    In room B we have a chest with a Key for "Exit Door"
     */
    public void helloModelTest() {
        Player player = new Player();

        Room roomA = new Room("Room A");
        Door door1 = new Door("to Room B");
        Chest chestA = new Chest("Chest A");
        Key key1 = new Key("to Room B");
        chestA.addItem(key1);
        roomA.addItem(chestA);
        roomA.addDoor(door1);

        assertEquals(1, roomA.getItems().size());
        assertEquals(1, roomA.getDoors().size());

        Room roomB = new Room("Room B");
        Chest chestB = new Chest("Chest B");
        Key key2 = new Key("Exit Door");
        chestB.addItem(key2);
        roomB.addItem(chestB);

        Door exitDoor = new Door("Exit Door");
        roomB.addDoor(exitDoor);

        assertEquals(1, roomB.getItems().size());
        assertEquals(1, roomB.getDoors().size());
        // Getting the chest from the Items in Room B and checking that the name of the Key (first item) is equals to the
        // name of the Door in that room
        assertTrue(((Chest)roomB.getItems().get(0)).getContent().get(0).getName().equals(roomB.getDoors().get(0).getId()));

        player.getInventory().add(key1);
        player.getInventory().add(key2);
        
        assertEquals(2, player.getInventory().size());
        
        
    }
}
