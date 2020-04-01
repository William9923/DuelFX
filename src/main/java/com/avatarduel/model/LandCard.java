package com.avatarduel.model;

import com.avatarduel.effect.DoNothingEffect;
import com.avatarduel.type.CardType;


public class LandCard extends Card {
    private int power;
    public LandCard(String id, String name, String element, String description, String image) {
        super(id, name, element, description, image);
        this.power = 1; // hard-coded without context power
        this.effect = new DoNothingEffect();
        this.type = CardType.LAND;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public void doEffect() {
        effect.showEffect();
    }

//    @Override -- > later implement || for debugging purpose
//    public void show() {
//
//    }
}
