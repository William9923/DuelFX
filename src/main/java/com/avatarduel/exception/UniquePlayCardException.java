package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;
import com.avatarduel.exception.InvalidOperationException;

public class UniquePlayCardException extends InvalidOperationException {
    public UniquePlayCardException(ExceptionCause cause) {
        super("Cannot Play Card", "Cause : " + cause.getCause());
    }
}
