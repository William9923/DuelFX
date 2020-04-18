package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.CardType;

public class InvalidPhaseCause implements ExceptionCause {
    private final CardType cardType;

    public InvalidPhaseCause(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public String getCause() {
        return "Cannot play "+ cardType.toString() + " in this phase";
    }
}
