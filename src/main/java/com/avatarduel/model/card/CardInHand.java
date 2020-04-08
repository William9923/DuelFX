package com.avatarduel.model.card;

import com.avatarduel.model.type.PlayerType;

public class CardInHand {
    private Card card;
    private int index;
    private PlayerType playerType;

    public CardInHand(Card card, int index, PlayerType playerType) {
        this.card = card;
        this.index = index;
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public int getIndex() {
        return index;
    }

    public Card getCard() {
        return card;
    }
}
