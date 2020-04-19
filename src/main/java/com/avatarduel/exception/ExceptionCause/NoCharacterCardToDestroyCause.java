package com.avatarduel.exception.ExceptionCause;

/**
 * Defines an exception cause, caused by no character to destroy
 */
public class NoCharacterCardToDestroyCause implements ExceptionCause {
    public NoCharacterCardToDestroyCause() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCause() {
        return "enemy has no character card to destroy";
    }
}
