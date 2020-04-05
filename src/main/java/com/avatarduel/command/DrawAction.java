package com.avatarduel.command;

import com.avatarduel.model.Game;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class DrawAction implements IAction{
    private PlayerType player;

    public DrawAction(PlayerType p) {
        player = p;

        if (validate()) {
            execute();
        }
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
        && Game.getInstance().getPlayerByType(player).getDeck().size() > 0); // kalo ada handsize limit, taro disini
    }
}
