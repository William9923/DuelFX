package com.avatarduel.model.player_component;

import com.avatarduel.model.card.Card;

import java.util.ArrayList;

public class Hand extends ArrayList<Card>{
    private int maxSize;
    public Hand(int size) {
        super();
    }

    @Override
    public boolean add(Card c) {
        if (this.size() < 10) {
            super.add(c);
            return true;
        }
        return false;
    }
}
