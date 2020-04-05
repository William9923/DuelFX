package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;


public class LandCard extends Card {
    private int power;
    public LandCard(int id, String name, Element element, String description, String image) {
        super(id, name, element, description, image);
        this.power = 1; // hard-coded without context power
        this.type = CardType.LAND;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

//    @Override -- > later implement || for debugging purpose
//    public void show() {
//
//    }
}
