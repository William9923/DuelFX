package com.avatarduel.exception;

import java.util.*;
import java.io.*;

public class NullCardException {
    private String message;
    private String cardType;

    public NullCardException(String cardType, String message){
        this.cardType = cardType;
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getCardType() { return cardType; }

    public void setMessage(String message) { this.message = message; }

    public void setCardType(String cardType) { this.cardType = cardType; }
}
