package com.avatarduel.exception;

import java.util.*;
import java.io.*;

public class InvalidActionException {
    private String action;
    private String message;

    public InvalidActionException(String action, String message){
        this.action = action;
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getAction() { return action; }

    public void setMessage(String message) { this.message = message; }

    public void setAction(String action) { this.action = action; }
}
