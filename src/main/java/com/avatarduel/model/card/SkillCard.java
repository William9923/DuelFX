package com.avatarduel.model.card;

public abstract class SkillCard extends Card{
    public SkillCard(String id, String name, String element, String description, String image) {
        super(id,name,element,description,image);
    }
    public abstract void show();
}
