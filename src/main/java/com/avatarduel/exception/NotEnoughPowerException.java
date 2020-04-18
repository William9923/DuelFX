package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;

public class NotEnoughPowerException extends InvalidOperationException {
    public NotEnoughPowerException(ExceptionCause cause) {
        super("Not enough power" , "Cause : " + cause.getCause());
    }
}
