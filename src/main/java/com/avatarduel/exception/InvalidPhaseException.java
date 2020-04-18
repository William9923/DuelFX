package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;
import com.avatarduel.model.type.Phase;

public class InvalidPhaseException extends  InvalidOperationException {
    public InvalidPhaseException(ExceptionCause cause) {
        super("Invalid Phase","Cause : " + cause.getCause());
    }
}
