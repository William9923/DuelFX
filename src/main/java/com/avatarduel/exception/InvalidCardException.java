package com.avatarduel.exception;

import com.avatarduel.model.card.Card;

import java.util.*;
import java.io.*;

public class InvalidCardException extends Exception{
    private String message;

    public InvalidCardException(String message){
        this.message = message;
    }

    public String getMessage() { return message; }

}
