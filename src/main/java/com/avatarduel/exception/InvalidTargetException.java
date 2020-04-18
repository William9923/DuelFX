package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

public class InvalidTargetException extends InvalidOperationException {
    public InvalidTargetException(ExceptionCause cause) {
        super("Invalid Target", "Cause : " + cause.getCause());
    }
}
