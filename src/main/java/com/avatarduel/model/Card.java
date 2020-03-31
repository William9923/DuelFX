package com.avatarduel.model;

import com.avatarduel.Loader;
import com.avatarduel.display.IDisplay;
import com.avatarduel.effect.IEffect;

public abstract class Card  {
    protected int id;
    protected String name;
    protected Element element;
    protected String description;
    protected String image;
    protected CardType type;
    protected IDisplay displayer;
    protected IEffect effect;

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

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public abstract void show();
    public abstract void doEffect();
}
