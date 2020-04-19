package com.avatarduel.model.card;

/**
 * SkillCardInField class has the same function/purpose as the CharacterCardInField.
 * SkillCardInField also the class that determines if the skill card can be used or not.
 * @author G10-K03-CardGameOOP
 */

public class SkillCardInField implements IField {

    private SkillCard card;
    private int createdAt;
    private int index;

    public SkillCardInField(SkillCard card, int createdAt, int index) {
        this.card = card;
        this.createdAt = createdAt;
        this.index = index;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public SkillCard getCard() {
        return card;
    }

    @Override
    public int getCreatedAtTurn() {
        return createdAt;
    }
}
