package com.avatarduel.model;

import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

// Singleton Design pattern
public class Game {
    private static Game game;
    private Player p1;
    private Player p2;
    private int currentTurn;
    private PlayerType currentPlayer;
    private Phase currentPhase;

    private Game() {
        this.p1 = new Player(PlayerType.A);
        this.p2 = new Player(PlayerType.B);
        this.currentTurn = 1; // first turn
        this.currentPhase = Phase.DRAW;
        this.currentPlayer = PlayerType.A;
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Player getPlayerByType(PlayerType type) {
        if (type.equals(PlayerType.A)) {
            return p1;
        } else if (type.equals(PlayerType.B)){ // else
            return p2;
        } else {
            return null; // throw error
        }
    }
}
