package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.PlayerType;

public class NoCharacterCardToDestroyCause implements ExceptionCause {
    public NoCharacterCardToDestroyCause() {
    }

    @Override
    public String getCause() {
        return "enemy has no character card to destroy";
    }
}
