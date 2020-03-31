package com.avatarduel.model;

import com.avatarduel.display.DisplayLand;

import java.util.HashMap;
import java.util.Map;

public class LandCard extends Card {
    private int power;
    public LandCard(String id, String name, String element, String description, String image) {
        super(id, name, element, description, image);
        this.power = 1; // hard-coded without context power
        this.displayer = new DisplayLand();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public void show() {
        HashMap<String, String> map = displayer.display(this);
        for (Map.Entry<String,String> entry : map.entrySet())
            System.out.println(entry.getKey() + " : "+ entry.getValue());
    }
}
