package com.avatarduel.model.type;

/**
 * An enum class only to distinguish the type of cards
 * @author G10-K03-CardGameOOP
 */

public enum CardType {
    CHARACTER("character"),
    SKILL_AURA("skill aura"),
    SKILL_DESTROY("skill destroy"),
    SKILL_POWER_UP("skill power up"),
    LAND("land"),
    NULL("null");

    private String cardType;
    private CardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return cardType;
    }
}
