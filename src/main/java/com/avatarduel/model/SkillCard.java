package com.avatarduel.model;

import com.avatarduel.display.DisplaySkill;
import com.avatarduel.effect.*;

import java.util.HashMap;
import java.util.Map;

public class SkillCard extends Card{

    private int power;
    private IEffect effect;

    // Aura Skill Card
    public SkillCard(String id, String name, String element, String description, String image, String attack, String defense, String power) {
        super(id, name, element, description, image);
        this.effect = new AuraEffect();
        this.displayer = new DisplaySkill();
        this.type = CardType.SKILL;
    }

    // Destroy Skill Card
    public SkillCard (String id, String name, String element, String description, String image, String power) {
        super(id,name,element,description,image);
        this.effect = new DestroyEffect();
        this.displayer = new DisplaySkill();
        this.type = CardType.SKILL;
    }

    // buat sementara untuk Power Up Skill Card
    public SkillCard(String id, String name, String element, String description, String image, String attack, String power) {
        super(id,name, element, description, image);
        this.effect = new PowerUpEffect();
        this.displayer = new DisplaySkill();
        this.type = CardType.SKILL;
    }

    @Override
    public void show() {
        HashMap<String, String> map = displayer.display(this);
        for (Map.Entry<String,String> entry : map.entrySet())
            System.out.println(entry.getKey() + " : "+ entry.getValue());
    }
    public void doEffect() {
        effect.showEffect();
    }

}
