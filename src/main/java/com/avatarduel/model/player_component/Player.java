package com.avatarduel.model.player_component;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.PlayerType;

import java.util.EmptyStackException;

public class Player {
    protected Deck deck;
    protected Field field;
    protected Hand hand;
    protected PlayerType type;
    protected PowerManager power;
    protected int healthPoint;
    public boolean hasPlayLand;

    // hard coded constant
    private static final int firstDraw = 7; // can be better coded, but right now, still hardcoded
    private static final int deckSize = 60;
    private static final int fieldSize = 6;
    private static final int initialHP = 80;

    public Player(PlayerType type) {
        this.deck = new Deck(deckSize, type); // decksize harus lebih dari 7
        this.field = new Field(fieldSize);
        this.hand = new Hand(10); // hand size harus lebih dari 7 maksimum
        this.type = type;
        this.power = new PowerManager();
        this.healthPoint = initialHP; // starting health point, bisa dinamik namun sekarang statik saja
        this.hasPlayLand = false;

        startGameDraw();
    }

    public void startGameDraw() throws EmptyStackException {
        for(int i = 0; i < firstDraw; i++){
            draw();
        }
    }

    public void draw() throws EmptyStackException {
        Card card = deck.draw();
        if (hand.size() < 10) {
            hand.add(card);
        }
    }

    public void removeCharacterFromFieldByID(int id)  {
        CharacterCardInField card = field.getCharacterCardByID(id);
        field.removeCharacterCard(card);
    }

    public Deck getDeck() {
        return deck;
    }

    public Field getField() {
        return field;
    }

    public Hand getHand() {
        return hand;
    }

    public PlayerType getType() {
        return type;
    }

    public PowerManager getPower() {
        return power;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public boolean checkLose() {
        return healthPoint <= 0;
    }

    public void refreshState() {
        this.power.refresh();
        this.getField().getCharCardList()
                .forEach(c -> c.refresh());
        this.hasPlayLand = false;
    }
}
