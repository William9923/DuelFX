package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.Phase;

/**
 * NextPhaseEvent is a event for changing the phase of a player into it's next.
 *
 * Notes :
 * If next phase is activated on battle phase, it will automatically activate EndPhaseEvent
 *
 * IMPORTANT NOTE:
 * This event will communicate with game singleton instantly, so there are no need to validate
 * In case where event is not possible to do, we throw exception so that the GUI Board can give the
 * error message to the player playing the games
 * @author G10-K03-CardGameOOP
 */

public class NextPhaseEvent implements IEvent {

    public NextPhaseEvent() {
    }

    /**
     * execute method to run the event invoked by user action
     */
    @Override
    public void execute() throws InvalidOperationException {
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        if (currPhase.equals(Phase.BATTLE)){
            IEvent event = new EndTurnEvent();
            event.execute();
        } else {
            Game.getInstance().nextPhase();
        }
    }

}
