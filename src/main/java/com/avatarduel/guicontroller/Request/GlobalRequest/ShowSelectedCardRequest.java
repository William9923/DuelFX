package com.avatarduel.guicontroller.Request.GlobalRequest;

import com.avatarduel.model.card.Card;

public class ShowSelectedCardRequest extends GlobalRequest{
    private final Card card;

    public ShowSelectedCardRequest(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
