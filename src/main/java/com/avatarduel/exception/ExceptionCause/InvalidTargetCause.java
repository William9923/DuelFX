package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.exception.InvalidTargetException;
import com.avatarduel.model.type.CardType;

public class InvalidTargetCause implements ExceptionCause {
    private final CardType cardType;

    public InvalidTargetCause(CardType cardType) {
        this.cardType = cardType;
    }
    @Override
    public String getCause() {
        return cardType.toString() + " cannot be used to the target";
    }
}
