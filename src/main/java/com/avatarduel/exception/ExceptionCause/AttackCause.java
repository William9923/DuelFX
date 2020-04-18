package com.avatarduel.exception.ExceptionCause;

public class AttackCause implements ExceptionCause {

    @Override
    public String getCause() {
        return "cannot attack";
    }
}
