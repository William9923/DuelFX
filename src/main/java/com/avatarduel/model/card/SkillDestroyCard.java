package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;

public class SkillDestroyCard extends Card {

    // Destroy Skill Card
    public SkillDestroyCard (String id, String name, String element, String description, String image, String power) {
        super(id,name,element,description,image);
        this.type = CardType.SKILL_DESTROY;
        this.power = Integer.parseInt(power);
    }

//    @Override -- > later implement || for debugging purpose
//    public void show() {
//
//    }
}
