package com.avatarduel.model.player_component;

import com.avatarduel.model.card.Card;
import com.avatarduel.util.*;

import java.util.*;

public class Deck {
    private CardDAO charDAO;
    private CardDAO landDAO;
    private CardDAO skillAuraDAO;
    // destroy DAO
    // power up DAO
    private int deckSize;
    private int currSize;
    private Stack<Card> cardStack;

    public Deck(int deckSize) {
        this.deckSize = deckSize;
        this.currSize = deckSize;
        this.charDAO = new CharacterDAO();
        this.landDAO = new LandDAO();
        this.skillAuraDAO = new SkillAuraDAO();
        this.cardStack = new Stack<Card>();
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
            cardStack.push(card);
        }
        for (int i = 0; i < Math.round(deckSize * 0.4) ; i++) {
            Card card = selectRandom(landCards);
            card.setId(j++);
            cardStack.push(card);
        }
        for (int i = 0; i < Math.round(deckSize * 0.2) ; i++) {
            Card card = selectRandom(skillCards);
            card.setId(j++);
            cardStack.push(card);
        }
        shuffle();
    }

    public int getCurrSize() {
        return currSize;
    }

    public int getDeckSize() {
        return deckSize;
    }

    private Card selectRandom(List<Card> cardList) {
        int size = cardList.size();
        Random rand = new Random();
        return cardList.get(rand.nextInt(size));
    }

    // shuffle
    public void shuffle() {
        Collections.shuffle(cardStack);
    }

    // draw
    public Card draw() {
        return cardStack.pop();
    }

    // peek
    public Card showFirstCard() {
        return cardStack.peek();
    }

    // printDeck : debugging function
    public void printDeck() {
        List<Card> listCard = new ArrayList<Card>(cardStack);
        Collections.reverse(listCard);
        for (Card card: listCard) {
            card.show();
        }
    }
}
