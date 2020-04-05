package com.avatarduel.command;

import com.avatarduel.model.Game;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

import static com.avatarduel.model.Game.getInstance;

public class EndTurnAction implements IAction{

    private PlayerType player;

    public EndTurnAction(PlayerType player) {
        this.player = player;

        if (this.validate()) {
            System.out.println("Check this out!");
            this.execute();
        } // throw error Action
    }
    @Override
    public void execute() {
        getInstance().nextPhase();
        getInstance().nextPlayer(); // ganti player
        getInstance().incrementTurn(); // naikin turn
        getInstance().nextPhase(); // ganti phase jadi draw
        getInstance().getPlayerByType(getInstance().getCurrentPlayer()).refreshState();  // refresh state next pemain
    }

    @Override
    public boolean validate() {
        return (getInstance().getCurrentPlayer() == (player)
                && getInstance().getCurrentPhase().getPhase() == Phase.MAIN2);
    }
}
