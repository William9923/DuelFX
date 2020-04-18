package com.avatarduel.mode;

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
