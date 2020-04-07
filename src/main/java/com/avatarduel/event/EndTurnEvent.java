package com.avatarduel.event;

import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

import static com.avatarduel.model.Game.getInstance;

public class EndTurnEvent implements IEvent {

    private PlayerType player;

    public EndTurnEvent(PlayerType player) {
        this.player = player;
    }
    @Override
    public void execute() {
        while(getInstance().getCurrentPhase().getPhase() != Phase.END) {
            getInstance().nextPhase();
        }
        getInstance().nextPlayer(); // Player A -> Player B, begitu sebaliknya
        getInstance().incrementTurn(); // Game State naikin turn + 1
        getInstance().nextPhase(); // EndPhase -> DrawPhase
        getInstance().getPlayerByType(getInstance().getCurrentPlayer()).refreshState();  // refresh state pemain pada draw phase
    }

    @Override
    public boolean validate() {
        return (getInstance().getCurrentPlayer() == (player));
    }
}
