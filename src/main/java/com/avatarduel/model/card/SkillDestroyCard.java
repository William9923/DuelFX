package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;

public class SkillDestroyCard extends SkillCard {

    // Destroy Skill Card
    public SkillDestroyCard (int id, String name, Element element, String description, String image, String power) {
        super(id,name,element,description,image);
        this.type = CardType.SKILL_DESTROY;
        this.power = Integer.parseInt(power);
    }

//    @Override //-- > later implement || for debugging purpose
//    public void show() {
//
//    }
}
