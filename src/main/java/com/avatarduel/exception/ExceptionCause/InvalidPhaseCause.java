package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.Phase;

/**
 * Defines an exception cause, caused by invalid phase
 */
public class InvalidPhaseCause implements ExceptionCause {
    /**
     * what phase the action should be in
     */
    private final Phase phase;

    public InvalidPhaseCause(Phase phase) {
        this.phase = phase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCause() {
        return "you can only do the action in phase " + phase.toString().toLowerCase();
    }
}
