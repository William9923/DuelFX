package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

public class EmptyFieldException extends InvalidOperationException {
    public EmptyFieldException(ExceptionCause cause) {
        super("Empty Field", "Cause : " + cause.getCause());
    }
}
