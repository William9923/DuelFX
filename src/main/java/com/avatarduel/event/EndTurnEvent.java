package com.avatarduel.event;

import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

import static com.avatarduel.model.Game.getInstance;

public class EndTurnEvent implements IEvent {

    public EndTurnEvent() {
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
        Phase currPhase = getInstance().getCurrentPhase().getPhase();
        return (currPhase.equals(Phase.MAIN) || currPhase.equals(Phase.BATTLE));
    }
}
