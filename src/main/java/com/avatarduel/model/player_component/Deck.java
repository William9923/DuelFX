package com.avatarduel.model.player_component;

import com.avatarduel.dao.CardDAO;
import com.avatarduel.dao.CharacterDAO;
import com.avatarduel.dao.LandDAO;
import com.avatarduel.dao.SkillAuraDAO;
import com.avatarduel.model.card.Card;

import java.util.*;

public class Deck extends Stack<Card>{
    private CardDAO charDAO;
    private CardDAO landDAO;
    private CardDAO skillAuraDAO;
    // destroy DAO
    // power up DAO
    private int deckSize;

    public Deck(int deckSize) {
        super();
        this.deckSize = deckSize;
        this.charDAO = new CharacterDAO();
        this.landDAO = new LandDAO();
        this.skillAuraDAO = new SkillAuraDAO();
        init();
    }

    // init
    private void init() {
        List<Card> charCards = charDAO.getAllCard();
        List<Card> landCards = landDAO.getAllCard();
        List<Card> skillCards = skillAuraDAO.getAllCard();
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
        int size = cardList.size();
        Random rand = new Random();
        return cardList.get(rand.nextInt(size));
    }

    // shuffle
    public void shuffle() {
        Collections.shuffle(this);
    }

    // draw
    public Card draw() {
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
        for (Card card: listCard) {
            card.show();
        }
    }
}
