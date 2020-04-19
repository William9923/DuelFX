package com.avatarduel.event;

import com.avatarduel.model.type.Phase;

import static com.avatarduel.model.Game.getInstance;

/**
 * EndTurnEvent is a event for ending a player turn.
 * This event cause the game instance to switch the current player into other player, and refresh all the states for other player (such as the power and character attack behavior)
 *
 * IMPORTANT NOTE:
 * This event will communicate with game singleton instantly, so there are no need to validate
 * In case where event is not possible to do, we throw exception so that the GUI Board can give the
 * error message to the player playing the games
 * @author G10-K03-CardGameOOP
 */

public class EndTurnEvent implements IEvent {

    public EndTurnEvent() {
    }

    /**
     * execute method to run the event invoked by user action
     */
    @Override
    public void execute()  {
        while(getInstance().getCurrentPhase().getPhase() != Phase.END) {
            getInstance().nextPhase();
        }
        getInstance().nextPlayer(); // Player A -> Player B, begitu sebaliknya
        getInstance().incrementTurn(); // Game State naikin turn + 1
        getInstance().nextPhase(); // EndPhase -> DrawPhase
        getInstance().getPlayerByType(getInstance().getCurrentPlayer()).refreshState();  // refresh state pemain pada draw phase
    }

    public boolean validate() {
        Phase currPhase = getInstance().getCurrentPhase().getPhase();
        return (currPhase.equals(Phase.MAIN) || currPhase.equals(Phase.BATTLE));
    }
}
