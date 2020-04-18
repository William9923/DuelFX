package com.avatarduel.mode;

import com.avatarduel.model.type.Element;

public abstract class SkillCard extends Card{
    public SkillCard(int id, String name, Element element, String description, String image) {
        super(id,name,element,description,image);
    }

    public void show() {
        System.out.println("ID : " + getId() + " Name : " + getName());
        System.out.println("Description : " + getDescription());
        System.out.println("Type : " + getType());
    }
}
