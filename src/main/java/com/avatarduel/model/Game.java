package com.avatarduel.model;

import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;
import com.avatarduel.phase.*;

// Singleton Design pattern
public class Game {
    private static Game game;
    private Player p1;
    private Player p2;
    private int currentTurn;
    private PlayerType currentPlayer;
    private IPhase currentPhase;

    private Game() {
        this.p1 = new Player(PlayerType.A);
        this.p2 = new Player(PlayerType.B);
        this.currentTurn = 1; // first turn
        this.currentPhase = new DrawPhase();
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
        return currentPhase.getPhase();
    }

    public void nextPhase() {
        currentPhase = currentPhase.next();
    }

    public void setCurrentPhase(Phase newPhase) {
        switch (newPhase) {
            case DRAW: currentPhase = new DrawPhase(); break;
            case MAIN1: currentPhase = new MainPhase1(); break;
            case MAIN2: currentPhase = new MainPhase2(); break;
            case BATTLE: currentPhase = new BattlePhase(); break;
            case END: currentPhase = new EndPhase(); break;
            // default:  / throw InvalidPhase
        }
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
