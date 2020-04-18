package com.avatarduel.exception.ExceptionCause;

public class MultipleLandCardPlayedOnTheSameTurnCause implements ExceptionCause {

    @Override
    public String getCause() {
        return "you can only play one land card per turn ";
    }
}
