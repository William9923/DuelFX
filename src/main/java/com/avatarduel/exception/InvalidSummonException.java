package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

/**
 * Exception because the player is trying to summon a card
 */
public class InvalidSummonException extends InvalidPlayCardException {
    public InvalidSummonException(ExceptionCause cause) {
        super("Invalid Summon", cause);
    }
}
