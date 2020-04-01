package com.avatarduel.model;

import com.avatarduel.type.CardType;
import com.avatarduel.type.CharacterState;
import com.avatarduel.type.PlayerType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            field.addCharacterCard(new CharacterCardInField((CharacterCard) card,state));  // masukin ke field
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
