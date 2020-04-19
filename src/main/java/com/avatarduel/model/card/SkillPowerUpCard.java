package com.avatarduel.model.card;

import com.avatarduel.factory.CardFactory;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;

/**
 * SkillPowerUpCard is one the unique SkillCard types that able to increase or decrease the attack
 * and defense of the character.
 * But if the targeted player attack the enemy which in defense state, the enemy's HP most likely
 * still got decreased/attacked.
 * @author G10-K03-CardGameOOP
 */

public class SkillPowerUpCard extends SkillCard {

    public SkillPowerUpCard(int id, String name, Element element, String description, String image, String power) {
        super(id,name, element, description, image);
        this.type = CardType.SKILL_POWER_UP;
        this.power = Integer.parseInt(power);
    }

    public SkillPowerUpCard(String[] elements) {
        this(Integer.parseInt(elements[0]), elements[1], CardFactory.outputElement(elements[2]), elements[3], elements[4], elements[5]);
    }
}
