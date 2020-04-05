package com.avatarduel.exception;

import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.PlayerType;

import java.util.*;
import java.io.*;

public class InvalidActionException extends Exception{
    private PlayerType player;
    private String message;

    public InvalidActionException(PlayerType player, String message){
        this.player = player;
        this.message = message;
    }

    public String getMessage() { return message; }

    public PlayerType getPlayer() {
        return player;
    }
}
