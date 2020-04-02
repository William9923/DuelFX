package com.avatarduel.dao;

import com.avatarduel.dao.CardDAO;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.SkillDestroyCard;
import com.avatarduel.util.Loader;

import java.util.ArrayList;
import java.util.List;

public class SkillDestroyDAO implements CardDAO {
    List<Card> cards;
    Loader loader;

    public SkillDestroyDAO() {
        cards = new ArrayList<Card>();
        loader = new Loader();
        cards = loader.loadSkillDestroy();
    }

    @Override
    public List<Card> getAllCard() {
        return new ArrayList<Card>(cards);
    }

    @Override
    public SkillDestroyCard getCardByIdx(int index) {
        return (SkillDestroyCard) cards.get(index);  // error ganti ya wil
    }

    @Override
    public SkillDestroyCard getCardById(int id) {
        return (SkillDestroyCard) cards.stream()
                .filter(card -> id == card.getId())
                .findAny()
                .orElse(null);
    }
}
