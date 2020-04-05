package com.avatarduel.model.player_component;

import com.avatarduel.model.card.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    // incase, nanti ada logic hand lagi gitu

    public void print() {
        this.forEach(s -> s.show());
    }
}
