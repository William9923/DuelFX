package com.avatarduel.model.card;

import com.avatarduel.model.type.PlayerType;

public class CardInHand {
    private Card card;
    private PlayerType playerType;

    public CardInHand(Card card, PlayerType playerType) {
        this.card = card;
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public Card getCard() {
        return card;
    }
}
