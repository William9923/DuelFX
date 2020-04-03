package com.avatarduel.exception;

import java.util.*;
import java.io.*;

public class InvalidPhaseException {
    private String phaseType;
    private String message;

    public InvalidPhaseException(String phaseType, String message){
        this.phaseType = phaseType;
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getPhaseType() { return phaseType; }

    public void setMessage(String message) { this.message = message; }

    public void setPhaseType(String phaseType) { this.phaseType = phaseType; }
}
