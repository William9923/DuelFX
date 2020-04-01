package com.avatarduel.util;

import com.avatarduel.model.Card;
import com.avatarduel.model.CharacterCard;
import com.avatarduel.model.SkillAuraCard;

import java.util.ArrayList;
import java.util.List;

public class SkillAuraDAO implements CardDAO{
    List<Card> cards;
    Loader loader;

    public SkillAuraDAO() {
        cards = new ArrayList<Card>();
        loader = new Loader();
        cards = loader.loadSkillAura();
    }

    @Override
    public List<Card> getAllCard() {
        return new ArrayList<Card>(cards);
    }

    @Override
    public SkillAuraCard getCardByIdx(int index) {
        return (SkillAuraCard) cards.get(index);  // error ganti ya wil
    }

    @Override
    public SkillAuraCard getCardById(int id) {
        return (SkillAuraCard) cards.stream()
                .filter(card -> id == card.getId())
                .findAny()
                .orElse(null);
    }
}
