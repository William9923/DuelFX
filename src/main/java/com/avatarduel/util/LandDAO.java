package com.avatarduel.util;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.LandCard;

import java.util.ArrayList;
import java.util.List;

public class LandDAO implements CardDAO{
    List<Card> cards;
    Loader loader;

    public LandDAO() {
        cards = new ArrayList<Card>();
        loader = new Loader();
        cards = loader.loadLand();
    }

    @Override
    public List<Card> getAllCard() {
        return new ArrayList<Card>(cards);
    }

    @Override
    public LandCard getCardByIdx(int index) {
        return (LandCard) cards.get(index);  // error ganti ya wil
    }

    @Override
    public LandCard getCardById(int id) {
        return (LandCard) cards.stream()
                .filter(card -> id == card.getId())
                .findAny()
                .orElse(null);
    }
}
