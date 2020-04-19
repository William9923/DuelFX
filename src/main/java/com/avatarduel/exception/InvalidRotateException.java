package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

/**
 * Exception because the player is trying to rotate character card
 */
public class InvalidRotateException extends InvalidOperationException {
    public InvalidRotateException(ExceptionCause cause) {
        super("Invalid Card Rotate Action", cause);
    }
}
