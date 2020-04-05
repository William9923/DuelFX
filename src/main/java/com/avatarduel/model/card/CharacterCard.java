package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;

public class CharacterCard extends Card {
    private int attack;
    private int defense;
    private int power;

    public CharacterCard(int id, String name, Element element, String description, String image, int attack, int defense, int power) {
        super(id, name, element, description, image);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.type = CardType.CHARACTER;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getPower() {
        return power;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setPower(int power) {
        this.power = power;
    }

//    @Override -- > later implement || for debugging purpose
//    public void show() {
//
//    }
}
