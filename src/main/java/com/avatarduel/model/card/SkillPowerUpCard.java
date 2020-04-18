package com.avatarduel.model.card;

import com.avatarduel.factory.CardFactory;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;

public class SkillPowerUpCard extends SkillCard {

    public SkillPowerUpCard(int id, String name, Element element, String description, String image, String power) {
        super(id,name, element, description, image);
        this.type = CardType.SKILL_POWER_UP;
        this.power = Integer.parseInt(power);
    }

    public SkillPowerUpCard(String[] elements) {
        this(Integer.parseInt(elements[0]), elements[1], CardFactory.outputElement(elements[2]), elements[3], elements[4], elements[5]);
    }

//    @Override //-- > later implement || for debugging purpose
//    public void show() {
//
//    }
}
