package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CharacterCardInField is a class for the character that in field states.
 * CharacterCardInField also has 2 states which are attack or defense that will
 * determine the process of the game.
 * @author G10-K03-CardGameOOP
 */

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
        this.hasAttacked = false; // karena kalo baru di summon ga bisa attack kan ya
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

    public void setConnectedCard(List<SkillCard> listCard) {
        this.connectedCard = listCard;
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

    /**
     * check if there is any skill card associated with this character
     * if yes return true, else return false
     * @return boolean
     */
    public boolean isPowerUp() {
        for (Card card : connectedCard) {
            if (card.getType() == CardType.SKILL_POWER_UP) {
                return true;
            }
        }
        return false;
    }

    /**
     * add a skill card to this character card
     * @param card the card attached
     */
    public void pair(SkillCard card) {
        connectedCard.add(card);
    }

    /**
     * @return total attack if the character is on attack state, else
     * return the total defense
     */
    public int getCurrentTotal() {
        if (getPosition().equals(CharacterState.ATTACK)) {
            return getTotalAttack();
        } else {
            return getTotalDefense();
        }
    }

    /**
     * this is actually important. Used for the upper class to tell whether
     * a character has passed a turn or not, if the character has passed a turn, then
     * it can attack, else throw an exception
     * @return the turn this card is created
     */
    public int getCreatedAtTurn() {
        return createdAtTurn;
    }

    public List<SkillCard> getConnectedCard() {
        return connectedCard;
    }

    /**
     * get total attack from buffs and debuff from other skill card
     * @return the total attack
     */
    public int getTotalAttack() {
        return Math.max(0, card.getAttack() + getBonusAttack());
    }

    /**
     * get total defense from buffs and debuff from other skill card
     * @return the total defense
     */
    public int getTotalDefense() {
        return Math.max(0, card.getDefense() + getBonusDefense());
    }

    /**
     * rotate the character from attack to defense or vice versa
     */
    public void switchPosition() {
        if (CharacterState.ATTACK.equals(position)) {
            position = CharacterState.DEFENSE;
        } else {
            position = CharacterState.ATTACK;
        }
    }

    /**
     * {@inheritDoc}
     * @return string that specifies this card
     */
    @Override
    public String toString() {
        return "ID:" + Integer.toString(this.getCard().getId()) + " | " + this.getCard().getName();
    }
}
