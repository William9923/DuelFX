package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CharacterCardInField {
    private CharacterCard card;
    private boolean hasAttacked;
    private CharacterState position;
    private List<SkillCard> connectedCard;
    private int createdAtTurn;

    public CharacterCardInField(CharacterCard card, CharacterState state, int createdAt) {
        this.card = card;
        this.position = state;
        this.hasAttacked = true; // karena kalo baru di summon ga bisa attack kan ya
        this.connectedCard = new ArrayList<>();
        this.createdAtTurn = createdAt;
    }

    public void attack() {
        hasAttacked = true;
    }

    public void refresh() {
        hasAttacked = false;
    }

    public boolean canAttack() {
        return !hasAttacked && position.equals(CharacterState.ATTACK);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(CharacterCard card) {
        this.card = card;
    }

    public int getBonusAttack() {
        int bonus = 0;
        for (Card card: connectedCard) {
            if (card.getType().equals(CardType.SKILL_AURA)) {
                bonus += ((SkillAuraCard) card).getAttack();
            }
        }
        return bonus;
    }

    public CharacterState getPosition() {
        return position;
    }

    public int getBonusDefense() {
        int bonus = 0;
        for (Card card: connectedCard) {
            if (card.getType().equals(CardType.SKILL_AURA)) {
                bonus += ((SkillAuraCard) card).getDefense();
            }
        }
        return bonus;
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
        Iterator itr = connectedCard.iterator();
        while (itr.hasNext())
        {
            Card itCard = (Card)itr.next();
            if (itCard.equals(card)) {
                itr.remove();
            }
        }
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

    public void changeState() {
        if (CharacterState.ATTACK.equals(position)) {
            position = CharacterState.DEFENSE;
        } else {
            position = CharacterState.ATTACK;
        }
    }
}
