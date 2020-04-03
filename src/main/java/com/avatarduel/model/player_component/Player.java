package com.avatarduel.model.player_component;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.PlayerType;

public class Player {
    protected Deck deck;
    protected Field field;
    protected Hand hand;
    protected PlayerType type;
    protected PowerManager power;
    protected int healthPoint;

    private static final int firstDraw = 7;

    public Player(PlayerType type) {
        this.deck = new Deck(60); // decksize harus lebih dari 7
        this.field = new Field(6);
        this.hand = new Hand(); // hand size harus lebih dari 7 maksimum
        this.type = type;
        this.power = new PowerManager();
        this.healthPoint = 80; // starting health point, bisa dinamik namun sekarang statik saja
    }

    public void startGameDraw() {
        for(int i = 0; i < firstDraw; i++){
            draw();
        }
    }

    public void draw() {
        hand.addCard(deck.draw());
    }

    public void playCharacterCard(int index, CharacterState state) {
        Card card = hand.findCardByIndex(index);
        if (card.getType().equals(CardType.CHARACTER)){ // kalo ga ini, maka throw error kalo wrong type
            hand.removeCardByIndex(index);  // keluarin dari tangan
            field.addCharacterCard(new CharacterCardInField((CharacterCard) card,state,0));  // masukin ke field
        } // else : throw error
    }

    public void playSkillAuraCard(int indexHand, int indexField) {
        if (hand.findCardByIndex(indexHand).getType().equals(CardType.SKILL_AURA)){
            Card card = hand.removeCardByIndex(indexHand);
            field.connectCards(field.getCharacterCardByIdx(indexField), card);
        } // else : throw error
    }

    // play skill destroy --> buat destroy ya, keknya dia mesti manggil game manager puny

    // play skill power up
    public void playSkillPowerUpCard(int indexHand, int indexField) {
        if (hand.findCardByIndex(indexHand).getType().equals(CardType.SKILL_POWER_UP)){
            Card card = hand.removeCardByIndex(indexHand);
            field.connectCards(field.getCharacterCardByIdx(indexField), card);
        } // else : throw error
    }

    public void removeCardFromHand(int index) {
        if (index < hand.getCardList().size()){
            hand.removeCardByIndex(index);
        } // else : throw error
    }

    public void removeCharacterFromField(int index) {
        if (index < field.getCharCardList().size()){
            field.removeCharacterCard(field.getCharacterCardByIdx(index));
        } // else : throw error
    }

    public void removeSkillCardFromField(int index) {
        if (index < field.getSkillCardList().size()){
            field.removeSkillCard(field.getSkillCardByIdx(index));
        } // else : throw error
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
}
