package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;

public class SkillPowerUpCard extends Card {

    public SkillPowerUpCard(String id, String name, String element, String description, String image, String power) {
        super(id,name, element, description, image);
        this.type = CardType.SKILL_POWER_UP;
        this.power = Integer.parseInt(power);
    }

//    @Override -- > later implement || for debugging purpose
//    public void show() {
//
//    }
}
