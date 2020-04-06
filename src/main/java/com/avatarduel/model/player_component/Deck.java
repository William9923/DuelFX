package com.avatarduel.model.player_component;

import com.avatarduel.dao.*;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.card.Card;

import java.util.*;

public class Deck extends Stack<Card>{
    private CardDAO csvDao;
    private int deckSize;

    public Deck(int deckSize) {
        super();
        this.deckSize = deckSize;
        this.csvDao = new CSVCardDAO();
        init();
    }

    // init
    private void init() {
        List<Card> charCards = csvDao.getAllCharacterCard();
        List<Card> landCards = csvDao.getAllLandCard();
        List<Card> skillCards = csvDao.getAllLandCard();

        int j = 0;
        for (int i = 0; i < Math.round(deckSize * 0.4) ; i++) {
            Card card = selectRandom(charCards);
            card.setId(j++);
            super.push(card);
        }
        for (int i = 0; i < Math.round(deckSize * 0.4) ; i++) {
            Card card = selectRandom(landCards);
            card.setId(j++);
            super.push(card);
        }
        for (int i = 0; i < Math.round(deckSize * 0.2) ; i++) {
            Card card = selectRandom(skillCards);
            card.setId(j++);
            super.push(card);
        }
        shuffle();
    }

    public int getTotalCard() {
        return deckSize;
    }

    private Card selectRandom(List<Card> cardList) {
        return cardList.get(new Random().nextInt(cardList.size()));
    }

    // shuffle
    public void shuffle() {
        Collections.shuffle(this);
    }

    // draw
    public Card draw() throws EmptyStackException{
        return super.pop();
    }

    // peek
    public Card showFirstCard() {
        return super.peek();
    }

    // printDeck : debugging function
    public void printDeck() {
        List<Card> listCard = new ArrayList<Card>(this);
        Collections.reverse(listCard);
        listCard.forEach(card -> card.show());
    }
}
