package com.avatarduel.model.player_component;

import com.avatarduel.model.type.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerManagerTest {

    private PowerManager manager;

    public PowerManagerTest() {

    }

    @Test
    public void testAddEnergy() {
        this.manager = new PowerManager();
        manager.add(Element.ENERGY,10);
        manager.add(Element.FIRE,10);
        manager.add(Element.WATER, 10);
        manager.add(Element.EARTH,10);
        manager.add(Element.AIR,10);

        assertEquals(10, manager.getCurrent(Element.ENERGY));
        assertEquals(10, manager.getCurrent(Element.FIRE));
        assertEquals(10, manager.getCurrent(Element.WATER));
        assertEquals(10, manager.getCurrent(Element.EARTH));
        assertEquals(10, manager.getCurrent(Element.AIR));
    }

    @Test
    public void testReduceEnergy() {
        this.manager = new PowerManager();
        manager.add(Element.ENERGY,10);
        manager.add(Element.FIRE,10);
        manager.add(Element.WATER, 10);
        manager.add(Element.EARTH,10);
        manager.add(Element.AIR,10);

        manager.reduce(Element.ENERGY,10);
        manager.reduce(Element.FIRE,10);
        manager.reduce(Element.WATER, 10);
        manager.reduce(Element.EARTH,10);
        manager.reduce(Element.AIR,10);

        assertEquals(0, manager.getCurrent(Element.ENERGY));
        assertEquals(0, manager.getCurrent(Element.FIRE));
        assertEquals(0, manager.getCurrent(Element.WATER));
        assertEquals(0, manager.getCurrent(Element.EARTH));
        assertEquals(0, manager.getCurrent(Element.AIR));

        assertEquals(10, manager.getTotal_air());
        assertEquals(10, manager.getTotal_fire());
        assertEquals(10, manager.getTotal_water());
        assertEquals(10, manager.getTotal_earth());
        assertEquals(10, manager.getTotal_energy());

    }



}