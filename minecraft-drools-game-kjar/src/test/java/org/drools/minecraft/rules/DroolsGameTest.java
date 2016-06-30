/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.rules;

import org.drools.minecraft.model.Chest;
import org.drools.minecraft.model.Door;
import org.drools.minecraft.model.Key;
import org.drools.minecraft.model.Player;
import org.drools.minecraft.model.Room;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 *
 * @author salaboy
 */
public class DroolsGameTest {

    public DroolsGameTest() {
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
    public void helloRules() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        KieSession kSession = kContainer.newKieBase( "escapetheroomKBase", null ).newKieSession();
        //kSession.addEventListener(new ModAgendaEventListener());
        //kSession.addEventListener(new ModRuleRuntimeEventListener());
        kSession.setGlobal("cmds", new MockCommandHelper());
        kSession.setGlobal("tests", new MockTestHelper());

        Player player = new Player("salaboy");

        FactHandle playerFactHandle = kSession.insert(player);

        Room roomA = new Room("Room A");
        roomA.addPlayer(player.getName());

        Door door1 = new Door("to Room B");
        
        kSession.insert(door1);
        
        Chest chestA = new Chest("Chest A");
        Key key1 = new Key("to Room B");
        chestA.addItem(key1);
        roomA.addItem(chestA);
        roomA.addDoor(door1);

        kSession.insert(roomA);

        Room roomB = new Room("Room B");
        Chest chestB = new Chest("Chest B");
        Key key2 = new Key("Exit Door");
        chestB.addItem(key2);
        roomB.addItem(chestB);

        Door exitDoor = new Door("Exit Door");
        kSession.insert(exitDoor);
        roomB.addDoor(exitDoor);

        kSession.insert(roomB);

        int fired = kSession.fireAllRules();

        //what is this?
        //Assert.assertEquals(1, fired);

        // Now the player goes to the chest and pick up Key1
        player.getInventory().add(key1);
        // I need to update the player, because the Inventory is not a fact
        kSession.update(playerFactHandle, player);

        fired = kSession.fireAllRules();

        //Assert.assertEquals(2, fired);

        //Assert.assertEquals(true, door1.isOpen());

    }
}
