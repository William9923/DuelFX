package com.avatarduel.model;

public class LandCard extends Card {
    private int power;
    public LandCard(String id, String name, String element, String description, String image) {
        super(id, name, element, description, image);
        this.power = 1; // hard-coded without context power
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public void display() {
        System.out.println("Card : " + this.id);
    }
}
