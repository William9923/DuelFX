package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.CardType;

public class FullBoardCause implements ExceptionCause {
    private final CardType cardType;

    public FullBoardCause(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public String getCause() {
        String fieldName = cardType == CardType.CHARACTER? "character field" : "skill field";
        return "full " + fieldName;
    }
}
