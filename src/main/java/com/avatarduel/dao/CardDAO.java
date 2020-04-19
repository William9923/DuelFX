package com.avatarduel.dao;

import com.avatarduel.model.card.Card;

import java.util.List;

/**
 * CardDao is an interface which use to CSVCardDao
 *
 * IMPORTANT NOTE:
 * CardDao only consists of many List of Card that consists of character card,
 * skill card, and land card.
 * @author G10-K03-CardGameOOP
 */

public interface CardDAO {
    public List<Card> getAllCard();
    public Card getCardById(int id);
    public List<Card> getAllCharacterCard();
    public List<Card> getAllSkillCard();
    public List<Card> getAllLandCard();
}
