package com.avatarduel.exception;

import com.avatarduel.command.IAction;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.PlayerType;

import java.util.*;
import java.io.*;

public class InvalidActionException extends Exception{
    private String action;
    private String message;

    public InvalidActionException(String action, String message){
        this.action = action;
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getAction() {
        return action;
    }
}
