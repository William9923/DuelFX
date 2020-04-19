package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

/**
 * Exception because the player is trying to play a land card
 */
public class InvalidPlayLandCardException extends InvalidPlayCardException {
    public InvalidPlayLandCardException(ExceptionCause cause) {
        super("Invalid Play Land Card", cause);
    }
}
