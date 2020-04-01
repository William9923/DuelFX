package com.avatarduel.model;

import com.avatarduel.util.Loader;

import java.util.*;

public class Deck {
    private Loader loader;
    private int deckSize;
    private int currSize;
    // nanti tambahin reference ke player mana
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
        List<Card> allCards = new ArrayList<Card>();
        allCards.addAll(charCards);
        allCards.addAll(landCards);
        allCards.addAll(skillCards);
        for (int i = 0; i < deckSize; i++) {
            Card card = selectRandom(allCards);
            card.setId(i+1);
            cardStack.push(card);
        }
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
