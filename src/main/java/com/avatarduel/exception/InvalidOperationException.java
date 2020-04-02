package com.avatarduel.exception;

import java.util.*;
import java.io.*;

public class InvalidOperationException extends Exception {
    private String message;
    private String operationType;

    public InvalidOperationException(String opType, String description){
        this.operationType = opType;
        this.message = description;
    }

    public String getMessage() { return message; }

    public String getOperationTypeType() { return operationType; }

    public void setMessage(String message) { this.message = message; }

    public void setOpType(String opType) { this.operationType = opType; }
}
