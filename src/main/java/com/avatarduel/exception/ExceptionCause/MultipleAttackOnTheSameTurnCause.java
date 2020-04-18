package com.avatarduel.exception.ExceptionCause;

public class MultipleAttackOnTheSameTurnCause extends  AttackCause {
    @Override
    public String getCause() {
        return super.getCause() + " twice on the same turn";
    }
}
