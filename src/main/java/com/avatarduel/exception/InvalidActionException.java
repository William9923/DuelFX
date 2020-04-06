package com.avatarduel.exception;

public class InvalidActionException extends Exception{
    private String action;
    private String message;

    public InvalidActionException(String action, String message){
        this.action = action;
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getAction() {
        return action;
    }
}
