package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.CardType;

public class NoCharacterCardInFieldCause implements ExceptionCause {
    private final CardType cardType;

    public NoCharacterCardInFieldCause(CardType cardType) {
        this.cardType = cardType;
    }
    @Override
    public String getCause() {
        return "cannot play " + cardType.toString() + " because there are no character card in field";
    }
}
