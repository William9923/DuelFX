package com.avatarduel.util;

import com.avatarduel.model.Card;

import java.util.List;

public interface CardDAO {
    public List<Card> getAllCard();
    public Card getCardByIdx(int index);
    public Card getCardById(int id);
}
