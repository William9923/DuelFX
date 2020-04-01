package com.avatarduel.model;

public class SkillCardInField {
    private Card card;
    private CharacterCardInField pairCard;
    private CardType type;
    // reference ke player

    public SkillCardInField(Card skillCard , CharacterCardInField pairCard, CardType type) {
        this.card = skillCard;
        this.pairCard = pairCard;
        this.type = type;
    }

    public void remove() {
        // manggil fungsi dari player --> remove
    }
}
