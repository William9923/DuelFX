package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

/**
 * Exception because the player is trying to play a card
 */
public class InvalidPlayCardException extends InvalidOperationException {
    protected InvalidPlayCardException(String operation, ExceptionCause cause) {
        super(operation, cause);
    }
    public InvalidPlayCardException(ExceptionCause cause) {
        super("Invalid Play Card", cause);
    }
}
