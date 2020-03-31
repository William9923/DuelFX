package com.avatarduel.model;

import com.avatarduel.display.DisplayCharacter;

import java.util.HashMap;
import java.util.Map;

public class CharacterCard extends Card{
    private int attack;
    private int defense;
    private int power;

    public CharacterCard(String id, String name, String element, String description, String image, String attack, String defense, String power) {
        super(id, name, element, description, image);
        this.attack = Integer.parseInt(attack);
        this.defense = Integer.parseInt(defense);
        this.power = Integer.parseInt(power);
        this.displayer = new DisplayCharacter();
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

    @Override
    public void show() {

        HashMap<String, String> map = displayer.display(this);
        for (Map.Entry<String,String> entry : map.entrySet())
            System.out.println(entry.getKey() + " : "+ entry.getValue());
    }
}
