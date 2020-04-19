package com.avatarduel.model.player_component;

import com.avatarduel.model.card.Card;

import java.util.ArrayList;

/**
 * Hand is one of component that used for to put stacks of cards before move to hand.
 * IMPORTANT NOTE:
 * We assume the maximum number cards hold in hand is 10.
 * @author G10-K03-CardGameOOP
 */

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
