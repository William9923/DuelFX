package com.avatarduel.model;

import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;
import com.avatarduel.phase.*;
import com.google.common.eventbus.EventBus;

// Singleton Design pattern
public class Game {
    private static Game game;
    private Player p1;
    private Player p2;
    private int currentTurn;
    private PlayerType currentPlayer;
    private IPhase currentPhase;
    private EventBus eventBus;

    private Game() {
        this.resetGame();
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    public PlayerType getCurrentPlayer() {
        return currentPlayer;
    }
    public PlayerType getCurrentOpponent() {
        switch (getCurrentPlayer()) {
            case A: return PlayerType.B;
            case B: return PlayerType.A;
        }
        return null; // throw game rusak wkwkw
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public IPhase getCurrentPhase() {
        return currentPhase;
    }

    public void incrementTurn() {
        this.currentTurn++;
    }

    public void nextPhase() {
        currentPhase = currentPhase.next();
    }

    public void nextPlayer() {
        switch (currentPlayer) {
            case A: currentPlayer = PlayerType.B; break;
            case B: currentPlayer = PlayerType.A; break;
        }
    }


    public void setCurrentPhase(Phase newPhase) {
        switch (newPhase) {
            case DRAW: currentPhase = new DrawPhase(); break;
            case MAIN: currentPhase = new MainPhase(); break;
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

    public EventBus getEventBus() {
        return eventBus;
    }

    public boolean checkGameOver() {
        return (p1.checkLose()||p2.checkLose());
    }

    public void resetGame() {
        this.p1 = new Player(PlayerType.A);
        this.p2 = new Player(PlayerType.B);
        this.currentTurn = 1; // first turn
        this.currentPhase = new DrawPhase();
        this.currentPlayer = PlayerType.A;
        this.eventBus = new EventBus();
    }
}
