package com.avatarduel.exception;

import java.util.*;
import java.io.*;

public class InvalidTurnException {
    private String turn;
    private String message;

    public InvalidTurnException(String turn, String message){
        this.turn = turn;
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getTurn() { return turn; }

    public void setMessage(String message) { this.message = message; }

    public void setTurn(String turn) { this.turn = turn; }
}
