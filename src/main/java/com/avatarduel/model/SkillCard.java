package com.avatarduel.model;

import com.avatarduel.effect.*;

public class SkillCard extends Card{

    private int power;
    private IEffect effect;
    public SkillCard(String id, String name, String element, String description, String image, String attack, String defense, String power) {
        super(id, name, element, description, image);
        this.effect = new AuraEffect();
    }

    public SkillCard (String id, String name, String element, String description, String image, String power) {
        super(id,name,element,description,image);
        this.effect = new DestroyEffect();
    }

    // buat sementara untuk Power Up Skill Card
    public SkillCard(String id, String name, String element, String description, String image, String attack, String power) {
        super(id,name, element, description, image);
        this.effect = new PowerUpEffect();
    }


    @Override
    public void display() {
        System.out.println("Card Skill: " + this.id);
    }

    public void doEffect() {
        effect.showEffect();
    }

}
