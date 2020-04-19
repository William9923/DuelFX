package com.avatarduel.model.card;

import com.avatarduel.model.type.Element;

/**
 * SkillCard is one of the card types that also has their own types(Aura, Destroy, and Power Up).
 * SKillCard commonly used for adding some of unique ability of the character card.
 * @author G10-K03-CardGameOOP
 */

public class SkillCard extends Card{
    public SkillCard(int id, String name, Element element, String description, String image) {
        super(id,name,element,description,image);
    }

    public void show() {
        System.out.println("ID : " + getId() + " Name : " + getName());
        System.out.println("Description : " + getDescription());
        System.out.println("Type : " + getType());
    }
}
