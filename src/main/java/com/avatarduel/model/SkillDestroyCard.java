package com.avatarduel.model;

import com.avatarduel.effect.DestroyEffect;
import com.avatarduel.effect.IEffect;

public class SkillDestroyCard extends Card{

    // Destroy Skill Card
    public SkillDestroyCard (String id, String name, String element, String description, String image, String power) {
        super(id,name,element,description,image);
        this.effect = new DestroyEffect();
        this.type = CardType.SKILL_DESTROY;
        this.power = Integer.parseInt(power);
    }

    @Override
    public void doEffect() {

    }

    @Override
    public void show() {

    }
}
