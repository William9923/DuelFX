package com.avatarduel.dao;

import com.avatarduel.dao.CardDAO;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.util.Loader;

import java.util.ArrayList;
import java.util.List;

public class CharacterDAO implements CardDAO {

    List<Card> cards;
    Loader loader;

    public CharacterDAO() {
        cards = new ArrayList<Card>();
        loader = new Loader();
        cards = loader.loadCharacter();
    }

    @Override
    public List<Card> getAllCard() {
        return new ArrayList<Card>(cards);
    }

    @Override
    public CharacterCard getCardByIdx(int index) {
        return (CharacterCard) cards.get(index);  // error ganti ya wil
    }

    @Override
    public CharacterCard getCardById(int id) {
        return (CharacterCard) cards.stream()
                .filter(card -> id == card.getId())
                .findAny()
                .orElse(null);
    }
}