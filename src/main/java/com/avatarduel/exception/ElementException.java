package com.avatarduel.exception;

import java.util.*;
import java.io.*;

public class ElementException {
    private String message;
    private String elementType;

    public ElementException(String elementType, String description){
        this.elementType = elementType;
        this.message = description;
    }

    public String getMessage() { return message; }

    public String getElementType() { return elementType; }

    public void setElementType(String elementType) { this.elementType = elementType; }

    public void setMessage(String message) { this.message = message; }
}
