package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

import java.util.List;

public class DrawEvent implements IEvent {
    private PlayerType playerType;
    public DrawEvent(PlayerType type) {
        this.playerType = type;
    }

    @Override
    public void execute() {
        Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).draw();
        Game.getInstance().nextPhase();  // pindah ke next phase
    }

    @Override
    public boolean validate() {
        return (Game.getInstance().getCurrentPhase().getPhase() == Phase.DRAW
        && Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getDeck().size() > 0      // deck size limit
        && Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHand().size() < 10);   // hand size limit
    }
}
