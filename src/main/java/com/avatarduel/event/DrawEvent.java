package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class DrawEvent implements IEvent {

    public DrawEvent() {

    }

    @Override
    public void execute() throws InvalidOperationException {

        if (Game.getInstance().getCurrentPhase().getPhase() != Phase.DRAW){
            throw new InvalidOperationException("Draw", "Not in Draw Phase");
        }

        if (Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getDeck().size() <= 0){
            throw new InvalidOperationException("Draw", "Not Enough Card to Draw");
        }

        Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).draw(); // kalo >= 10, dia langsung ke buang kartuny
        Game.getInstance().nextPhase();  // pindah ke next phase
    }

    @Override
    public boolean validate() {
        return (Game.getInstance().getCurrentPhase().getPhase() == Phase.DRAW
        && Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getDeck().size() > 0      // deck size limit
        && Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHand().size() < 10);   // hand size limit
    }
}
