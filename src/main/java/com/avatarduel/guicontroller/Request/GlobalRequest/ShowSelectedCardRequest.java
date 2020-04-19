package com.avatarduel.guicontroller.Request.GlobalRequest;

import com.avatarduel.model.card.Card;

/**
 * {@inheritDoc}
 * tells the board controller to show the selected card
 * @author G10-K03-CardGameOOP
 */
public class ShowSelectedCardRequest extends GlobalRequest{
    private final Card card;

    /**
     * @param card show what card needed to be shown
     */
    public ShowSelectedCardRequest(Card card) {
        this.card = card;
    }

    /**
     * returns the card so the receiver know what card to be played
     * @return the card
     */
    public Card getCard() {
        return card;
    }
}
