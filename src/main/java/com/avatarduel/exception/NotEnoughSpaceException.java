package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;
import com.avatarduel.model.type.CardType;

public class NotEnoughSpaceException extends InvalidOperationException {
    public NotEnoughSpaceException(ExceptionCause cause) {
        super("Not Enough Space" , "Cause : " + cause.getCause());
    }
}
