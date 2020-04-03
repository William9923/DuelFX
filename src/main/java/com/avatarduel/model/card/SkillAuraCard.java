package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;

public class SkillAuraCard extends SkillCard {

    private int attack;
    private int defense;

    // Aura Skill Card
    public SkillAuraCard(String id, String name, String element, String description, String image, String attack, String defense, String power) {
        super(id, name, element, description, image);
        this.type = CardType.SKILL_AURA;
        this.power = Integer.parseInt(power);
        this.attack = Integer.parseInt(attack);
        this.defense = Integer.parseInt(defense);
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
//
//    @Override //-- > later implement || for debugging purpose
//    public void show() {
//
//    }
}
