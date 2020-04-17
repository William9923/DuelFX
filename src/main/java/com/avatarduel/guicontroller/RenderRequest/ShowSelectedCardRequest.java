package com.avatarduel.guicontroller.RenderRequest;

import com.avatarduel.model.card.Card;

public class ShowSelectedCardRequest {
    private final Card card;

    public ShowSelectedCardRequest(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}