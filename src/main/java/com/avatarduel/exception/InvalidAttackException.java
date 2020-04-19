package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

/**
 * Exception because the player is trying to attack
 */
public class InvalidAttackException extends InvalidOperationException {
    public InvalidAttackException(ExceptionCause cause) {
        super("Invalid Attack", cause);
    }
}
