package com.avatarduel.event;

import com.avatarduel.model.Game;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class DrawEvent implements IEvent {
    private PlayerType player;

    public DrawEvent(PlayerType p) {
        player = p;
    }

    @Override
    public void execute() {
        Game.getInstance().getPlayerByType(player).draw();
        Game.getInstance().nextPhase();  // pindah ke next phase
    }

    @Override
    public boolean validate() {
        return (Game.getInstance().getCurrentPlayer() == player
        && Game.getInstance().getCurrentPhase().getPhase() == Phase.DRAW
        && Game.getInstance().getPlayerByType(player).getDeck().size() > 0      // deck size limit
        && Game.getInstance().getPlayerByType(player).getHand().size() < 10);   // hand size limit
    }
}
