package com.avatarduel.model.card;

import com.avatarduel.effect.DestroyEffect;
import com.avatarduel.model.card.Card;
import com.avatarduel.type.CardType;

public class SkillDestroyCard extends Card {

    // Destroy Skill Card
    public SkillDestroyCard (String id, String name, String element, String description, String image, String power) {
        super(id,name,element,description,image);
        this.effect = new DestroyEffect();
        this.type = CardType.SKILL_DESTROY;
        this.power = Integer.parseInt(power);
    }

    @Override
    public void doEffect() {
        effect.showEffect();
    }

//    @Override -- > later implement || for debugging purpose
//    public void show() {
//
//    }
}
