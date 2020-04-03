package com.avatarduel.exception;

public class PlayerException {
    private String player;
    private String message;

    public PlayerException(String player, String message){
        this.player = player;
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getPlayer() { return player; }

    public void setMessage(String message) { this.message = message; }

    public void setPlayer(String player) { this.player = player; }
}
