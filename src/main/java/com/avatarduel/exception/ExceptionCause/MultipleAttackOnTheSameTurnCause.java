package com.avatarduel.exception.ExceptionCause;

/**
 * Defines an exception cause, caused by trying to command a character to attack several times in one turn
 */
public class MultipleAttackOnTheSameTurnCause extends  AttackCause {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCause() {
        return super.getCause() + " twice on the same turn";
    }
}
