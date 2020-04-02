package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CharacterCardInField {
    private CharacterCard card;
    public boolean hasAttacked;
    private CharacterState state;
    private List<Card> connectedCard;

    public CharacterCardInField(CharacterCard card, CharacterState state) {
        this.card = card;
        this.state = state;
        this.hasAttacked = true; // karena kalo baru di summon ga bisa attack kan ya
        this.connectedCard = new ArrayList<>();
    }

    public void refresh() {
        hasAttacked = false;
    }

    public boolean canAttack() {
        return !hasAttacked && state.equals(CharacterState.ATTACK);
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

    public void pair(Card card) {
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

    public List<Card> getConnectedCard() {
        return connectedCard;
    }

    public int getTotalAttack() {
        return Math.max(0, card.getAttack() + getBonusAttack());
    }

    public int getTotalDefense() {
        return Math.max(0, card.getDefense() + getBonusDefense());
    }

    public void changeState() {
        if (CharacterState.ATTACK.equals(state)) {
            state = CharacterState.DEFENSE;
        } else {
            state = CharacterState.ATTACK;
        }
    }
}
