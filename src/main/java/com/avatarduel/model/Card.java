package com.avatarduel.model;

import com.avatarduel.type.CardType;
import com.avatarduel.util.Loader;
import com.avatarduel.effect.IEffect;

public abstract class Card  {
    protected int id;
    protected String name;
    protected Element element;
    protected String description;
    protected String image;
    protected CardType type;
    protected IEffect effect;
    protected int power;

    // constructor
    public Card(String id, String name, String element, String description, String image) {
        this.id = Integer.parseInt(id);
        this.name = name;
        this.element = Loader.outputElement(element);
        this.description = description;
        this.image = image;
    }

    // getter & setter
    public Element getElement() {
        return element;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public IEffect getEffect() {
        return effect;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public void setEffect(IEffect effect) {
        this.effect = effect;
    }

    public abstract void doEffect();

    // debugging function
    public void show() {
        System.out.println("ID : " + getId() + " Name : " + getName());
        System.out.println("Description : " + getDescription());
        System.out.println("Type : " + getType());
    }
}
