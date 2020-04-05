package com.avatarduel.exception;

import com.avatarduel.model.card.Card;

import java.util.*;
import java.io.*;

public class InvalidCardException extends Exception{
    private String message;
    private Card card;

    public InvalidCardException(String message, Card cardError){
        this.card = cardError;
        this.message = message;
    }

    public String getMessage() { return message; }

    public Card getCard() {
        return card;
    }
}
