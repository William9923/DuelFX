package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

public class InvalidAttackException extends InvalidOperationException {
    public InvalidAttackException(ExceptionCause cause) {
        super("Invalid Attack", cause.getCause());
    }
}
