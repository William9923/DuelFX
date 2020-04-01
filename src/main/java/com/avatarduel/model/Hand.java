package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hand {

    List<Card> cardList;
    // nanti nambahin reference ke player mana

    public Hand() {
        this.cardList = new ArrayList<Card>();
    }

    public Card findCardById(int id) {
        // java stream api
        return cardList.stream()
                .filter(card -> id == card.getId())
                .findAny()
                .orElse(null);
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public boolean contain(Card card) {
        return cardList.contains(card);
    }

    public Card findCardByIndex(int index) {
        final Card card = cardList.get(index);
        return card;
    }

    public void addCard(Card newCard) {
        // kalo ada batesan jumlah kartu -> ini nanti di if - in aja
        cardList.add(newCard);
    }

    public Card removeCard(Card card) {
        if (cardList.size() > 0) {
            Iterator itr = cardList.iterator();
            while (itr.hasNext())
            {
                Card itCard = (Card)itr.next();
                if (itCard.equals(card)) {
                    itr.remove();
                    return itCard;
                }
            }
        }
        return null; // harusnya throw error
    }

    public Card removeCardByIndex(int index) {
        if (cardList.size() > index) {
            return cardList.remove(index);
        }
        return null; // harusnya throw error
    }

    public int cardInHand() {
        return cardList.size();
    }
}
