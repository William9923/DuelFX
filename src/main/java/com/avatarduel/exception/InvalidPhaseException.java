package com.avatarduel.exception;

import java.util.*;
import java.io.*;

public class InvalidPhaseException extends Exception{
    private String phaseType;
    private String message;

    public InvalidPhaseException(String phaseType, String message){
        this.phaseType = phaseType;
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getPhaseType() { return phaseType; }

}
