package com.avatarduel.exception;

//Exception to use in the backend game
public class InvalidOperationException extends Exception {
    private String action;
    private String message;

    //Constructor the exception which consist of the action and message
    public InvalidOperationException(String action, String message){
        this.action = action;
        this.message = message;
    }

    //Method to return the message of the exception
    public String getMessage() { return message; }

    //Method to return the action/operation of the exception
    public String getOperation() {
        return action;
    }
}
