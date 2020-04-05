package com.avatarduel.exception;

public class InvalidOperationException extends Exception {
    private String action;
    private String message;

    public InvalidOperationException(String action, String message){
        this.action = action;
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getOperation() {
        return action;
    }
}
