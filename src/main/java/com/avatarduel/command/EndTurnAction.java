package com.avatarduel.command;

import com.avatarduel.model.Game;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

import static com.avatarduel.model.Game.getInstance;

public class EndTurnAction implements IAction{

    private PlayerType player;

    public EndTurnAction(PlayerType player) {
        this.player = player;
    }
    @Override
    public void execute() {
        getInstance().nextPhase(); // MainPhase 2 -> EndPhase
        getInstance().nextPlayer(); // Player A -> Player B, begitu sebaliknya
        getInstance().incrementTurn(); // Game State naikin turn + 1
        getInstance().nextPhase(); // EndPhase -> DrawPhase
        getInstance().getPlayerByType(getInstance().getCurrentPlayer()).refreshState();  // refresh state pemain pada draw phase
    }

    @Override
    public boolean validate() {
        return (getInstance().getCurrentPlayer() == (player)
                && getInstance().getCurrentPhase().getPhase() == Phase.MAIN2);
    }
}
