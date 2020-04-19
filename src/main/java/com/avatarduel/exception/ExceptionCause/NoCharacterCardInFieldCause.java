package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.CardType;

/**
 * Defines an exception cause, caused by no character card in field
 */
public class NoCharacterCardInFieldCause implements ExceptionCause {
    /**
     * cardType used to print what cannot be played
     */
    private final CardType cardType;

    /**
     * @param cardType initialize private CardType cardType
     */
    public NoCharacterCardInFieldCause(CardType cardType) {
        this.cardType = cardType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCause() {
        return "cannot play " + cardType.toString() + " because there are no character card in field";
    }
}
