package com.avatarduel.dao;

import com.avatarduel.dao.CardDAO;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.SkillPowerUpCard;
import com.avatarduel.util.Loader;

import java.util.ArrayList;
import java.util.List;

public class SkillPowerUpDAO implements CardDAO {
    List<Card> cards;
    Loader loader;

    public SkillPowerUpDAO() {
        cards = new ArrayList<Card>();
        loader = new Loader();
        cards = loader.loadSkillPowerUp();
    }

    @Override
    public List<Card> getAllCard() {
        return new ArrayList<Card>(cards);
    }

    @Override
    public SkillPowerUpCard getCardByIdx(int index) {
        return (SkillPowerUpCard) cards.get(index);  // error ganti ya wil
    }

    @Override
    public SkillPowerUpCard getCardById(int id) {
        return (SkillPowerUpCard) cards.stream()
                .filter(card -> id == card.getId())
                .findAny()
                .orElse(null);
    }
}
