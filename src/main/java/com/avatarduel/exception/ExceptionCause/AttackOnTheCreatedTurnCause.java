package com.avatarduel.exception.ExceptionCause;

/**
 * Defines an exception cause, caused by attacking on the same turn a character is created
 */
public class AttackOnTheCreatedTurnCause extends AttackCause {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCause() {
        return super.getCause() + " on the same turn this character is created";
    }
}
