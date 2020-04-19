package com.avatarduel.exception.ExceptionCause;


/**
 * Defines an exception cause, caused by attacking
 */

public abstract class AttackCause implements ExceptionCause {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCause() {
        return "cannot attack";
    }
}
