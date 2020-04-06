package com.avatarduel.dao;

import com.avatarduel.model.card.Card;

import java.util.List;

public interface CardDAO {
    public List<Card> getAllCard();
    public Card getCardById(int id);
    public List<Card> getAllCharacterCard();
    public List<Card> getAllSkillCard();
    public List<Card> getAllLandCard();
}
