package com.avatarduel.exception.ExceptionCause;

public class AttackOnTheCreatedTurnCause extends AttackCause {
    @Override
    public String getCause() {
        return super.getCause() + " on the same turn this character is created";
    }
}
