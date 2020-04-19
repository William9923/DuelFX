package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

/**
 * Base exception used for the game, Invalid operation means that
 * the operation the player is doing cannot be done
 */
public abstract class InvalidOperationException extends Exception {
    private String operation;
    private String message;

    /**
     * Constructor the exception which consist of the action and message
     * @param operation the operation player is trying to do
     * @param cause the cause of the exception
     */
    public InvalidOperationException(String operation, ExceptionCause cause){
        this.operation = operation;
        this.message = "Cause : " + cause.getCause();
    }

    /**
     * Method to return the message of the exception
     * @return String
     */
    @Override
    public String getMessage() { return message; }

    /**
     * Method to return the action/operation of the exception
     * @return String
     */
    public String getOperation() {
        return operation;
    }
}
