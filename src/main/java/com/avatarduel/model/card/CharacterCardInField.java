package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CharacterCardInField implements IField{
    private CharacterCard card;
    public boolean hasAttacked;
    private CharacterState position;
    private List<SkillCard> connectedCard;
    private int createdAtTurn;
    private int index; // index in field for gui

    public CharacterCardInField(CharacterCard card, CharacterState state, int createdAt, int index) {
        this.card = card;
        this.position = state;
        this.hasAttacked = true; // karena kalo baru di summon ga bisa attack kan ya
        this.connectedCard = new ArrayList<>();
        this.createdAtTurn = createdAt;
        this.index = index;
    }


    public void refresh() {
        hasAttacked = false;
    }

    public boolean canAttack() {
        return !hasAttacked && position.equals(CharacterState.ATTACK);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Card getCard() {
        return card;
    }

    public void setCard(CharacterCard card) {
        this.card = card;
    }

    public int getBonusAttack() {
        AtomicInteger bonus = new AtomicInteger();
        for (Card card: connectedCard) {
            if (card.getType().equals(CardType.SKILL_AURA)) {
                bonus.addAndGet(((SkillAuraCard) card).getAttack());
            }
        }
        return bonus.get();
    }

    @Override
    public String toString() {
        return this.getCard().getId() + " | " + this.getCard().getName();
    }

    public CharacterState getPosition() {
        return position;
    }

    public int getBonusDefense() {
        AtomicInteger bonus = new AtomicInteger();
        for (Card card: connectedCard) {
            if (card.getType().equals(CardType.SKILL_AURA)) {
                bonus.addAndGet(((SkillAuraCard) card).getDefense());
            }
        }
        return bonus.get();
    }

    public boolean isPowerUp() {
        for (Card card : connectedCard) {
            if (card.getType().equals(CardType.SKILL_POWER_UP)) {
                return true;
            }
        }
        return false;
    }

    public void pair(SkillCard card) {
        connectedCard.add(card);
    }

    public void removePair(Card card) {
        connectedCard = connectedCard.stream()
                .filter(c -> !card.equals(c))
                .collect(Collectors.toList());
    }

    public int getCurrentTotal() {
        if (getPosition().equals(CharacterState.ATTACK)) {
            return getTotalAttack();
        } else {
            return getTotalDefense();
        }
    }

    public int getCreatedAtTurn() {
        return createdAtTurn;
    }

    public List<SkillCard> getConnectedCard() {
        return connectedCard;
    }

    public int getTotalAttack() {
        return Math.max(0, card.getAttack() + getBonusAttack());
    }

    public int getTotalDefense() {
        return Math.max(0, card.getDefense() + getBonusDefense());
    }

    public void switchPosition() {
        if (CharacterState.ATTACK.equals(position)) {
            position = CharacterState.DEFENSE;
        } else {
            position = CharacterState.ATTACK;
        }
    }
}
