package com.avatarduel.exception;

import java.util.*;
import java.io.*;

public class InvalidElementException extends Exception{
    private String message;
    private String elementType;

    public InvalidElementException(String elementType, String description){
        this.elementType = elementType;
        this.message = description;
    }

    public String getMessage() { return message; }

    public String getElementType() { return elementType; }

}
