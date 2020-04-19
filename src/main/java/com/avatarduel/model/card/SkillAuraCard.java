package com.avatarduel.model.card;

import com.avatarduel.factory.CardFactory;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;

/**
 * SkillAuraCard is one the SkillCard types that able to increase or decrease the attack
 * and defense of the character.
 * @author G10-K03-CardGameOOP
 */

public class SkillAuraCard extends SkillCard {

    private int attack;
    private int defense;

    // Aura Skill Card
    //id	name	element	description	imagepath	power	attack	defense
    public SkillAuraCard(int id, String name, Element element, String description, String image, int power, int attack, int defense) {
        super(id, name, element, description, image);
        this.type = CardType.SKILL_AURA;
        this.power = power;
        this.attack = attack;
        this.defense = defense;
    }

    public SkillAuraCard(String[] elements) {
        this(Integer.parseInt(elements[0]), elements[1], CardFactory.outputElement(elements[2]), elements[3], elements[4],
                Integer.parseInt(elements[5]), Integer.parseInt(elements[6]), Integer.parseInt(elements[7]));
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
