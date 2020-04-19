package com.avatarduel.exception.ExceptionCause;

/**
 * Defines an exception cause, caused by trying to play multiple land card in a turn
 */
public class MultipleLandCardPlayedOnTheSameTurnCause implements ExceptionCause {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCause() {
        return "you can only play one land card per turn";
    }
}
