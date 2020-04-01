package com.avatarduel.model;

import com.avatarduel.util.Loader;

import java.util.*;

public class Deck {
    private Loader loader;
    private int deckSize;
    private int currSize;
    private Stack<Card> cardStack;

    public Deck(int deckSize) {
        this.deckSize = deckSize;
        this.currSize = deckSize;
        this.loader = new Loader();
        this.cardStack = new Stack<Card>();
        init();
    }

    // init
    private void init() {
        List<Card> charCards = loader.loadCharacter();
        List<Card> landCards = loader.loadLand();
        List<Card> skillCards = loader.loadSkillAura();
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
