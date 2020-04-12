package com.avatarduel.model.player_component;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.card.*;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
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

    public void playCharacterCardByID(int id, CharacterState state, int turn, int index) {
        Card card = hand.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if (card.getType().equals(CardType.CHARACTER) && card != null) {
            hand.remove(card);
            field.addCharacterCard(new CharacterCardInField((CharacterCard) card, state, turn, index));
        }
    }

    public void playSkillCardByID (int id, int charID, int index, int turn) {
        SkillCard skillCard = (SkillCard) hand.stream()
                .filter(c -> c.getId() == id && (c.getType().equals(CardType.SKILL_AURA) || c.getType().equals(CardType.SKILL_POWER_UP)))
                .findFirst()
                .orElse(null);

        CharacterCardInField playCard = field.getCharCardList().stream()
                .filter(c -> c.getCard().getId() == charID)
                .findFirst()
                .orElse(null);

        if ((skillCard != null) && (playCard != null) && (getField().getSkillCardList().size() < getField().getFieldSize())) {
            hand.remove(skillCard);
            field.connectCards(playCard, (SkillCard) skillCard, index, turn);
            field.addSkillCard(skillCard, index, turn);
        }
    }

    public void removeCharacterFromFieldByID(int id)  {
        CharacterCardInField card = field.getCharacterCardByID(id);
//        if (card == null) {
//            throw new InvalidOperationException("Removing Summoned Character", "Card not found");
//        }
        field.removeCharacterCard(card);
    }

    public void removeSkillCardByID (int id) {
        SkillCard card = (SkillCard) field.getSkillCardByID(id).getCard();
//        if (card == null) {
//            throw new InvalidOperationException("Removing Summoned Character", "Card not found");
//        }
        field.removeSkillCard(card);
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
