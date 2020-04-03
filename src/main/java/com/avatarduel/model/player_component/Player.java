package com.avatarduel.model.player_component;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCard;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.PlayerType;

import java.util.ArrayList;

public class Player {
    protected Deck deck;
    protected Field field;
    protected Hand hand;
    protected PlayerType type;
    protected PowerManager power;
    protected int healthPoint;

    // hard coded constant
    private static final int firstDraw = 7; // can be better coded, but right now, still hardcoded
    private static final int deckSize = 60;
    private static final int fieldSize = 6;
    private static final int initialHP = 80;

    public Player(PlayerType type) {
        this.deck = new Deck(deckSize); // decksize harus lebih dari 7
        this.field = new Field(fieldSize);
        this.hand = new Hand(); // hand size harus lebih dari 7 maksimum
        this.type = type;
        this.power = new PowerManager();
        this.healthPoint = initialHP; // starting health point, bisa dinamik namun sekarang statik saja
    }

    public void startGameDraw() {
        for(int i = 0; i < firstDraw; i++){
            draw();
        }
    }

    public void draw() {
        hand.add(deck.draw());
    }

    public void playCharacterCard(int index, CharacterState state, int turn) {
        Card card = hand.get(index);
        if (card.getType().equals(CardType.CHARACTER)){ // kalo ga ini, maka throw error kalo wrong type
            hand.remove(index);  // keluarin dari tangan
            field.addCharacterCard(new CharacterCardInField((CharacterCard) card,state, turn));  // masukin ke field
        } // else : throw error
    }

    public void playSkillAuraCard(int indexHand, int indexField) {
        if (hand.get(indexHand).getType().equals(CardType.SKILL_AURA)){
            Card card = hand.remove(indexHand);
            field.connectCards(field.getCharacterCardByIdx(indexField), (SkillCard) card);
        } // else : throw error
    }

    // play skill destroy --> buat destroy ya, keknya dia mesti manggil game manager puny

    // play skill power up
    public void playSkillPowerUpCard(int indexHand, int indexField) {
        if (hand.get(indexHand).getType().equals(CardType.SKILL_POWER_UP)){
            SkillCard card = (SkillCard) hand.remove(indexHand);
            field.connectCards(field.getCharacterCardByIdx(indexField), card);
        } // else : throw error
    }

    public void removeCardFromHand(int index) {
        if (index < hand.size()){
            hand.remove(index);
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
