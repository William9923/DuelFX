package com.avatarduel.model;

import com.avatarduel.effect.PowerUpEffect;

public class SkillPowerUpCard extends Card{

    public SkillPowerUpCard(String id, String name, String element, String description, String image, String power) {
        super(id,name, element, description, image);
        this.effect = new PowerUpEffect();
        this.type = CardType.SKILL_POWER_UP;
        this.power = Integer.parseInt(power);
    }

    @Override
    public void doEffect() {

    }

    @Override
    public void show() {

    }
}
