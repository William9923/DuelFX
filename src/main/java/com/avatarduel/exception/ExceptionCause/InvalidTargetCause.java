package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.CardType;

/**
 * Defines an exception cause, caused by full board
 */
public class InvalidTargetCause implements ExceptionCause {

    /**
     * cardType used to print what part of the field is full (skill field or character field)
     */
    private final CardType cardType;

    /**
     * @param cardType initialize private CardType cardType
     */
    public InvalidTargetCause(CardType cardType) {
        this.cardType = cardType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCause() {
        return cardType.toString() + " cannot be used to the target";
    }
}
