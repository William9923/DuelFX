package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.CardType;

/**
 * Defines an exception cause, caused by full board
 */
public class FullBoardCause implements ExceptionCause {
    /**
     * cardType used to print what part of the field is full (skill field or character field)
     */
    private final CardType cardType;

    /**
     * @param cardType initialize private CardType cardType
     */
    public FullBoardCause(CardType cardType) {
        this.cardType = cardType;
    }

    /**
     * {@inheritDoc}
     * @if the cardType is character, returns full character field
     * @else returns full skill field
     */
    @Override
    public String getCause() {
        String fieldName = cardType == CardType.CHARACTER? "character field" : "skill field";
        return "full " + fieldName;
    }
}
