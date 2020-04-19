package com.avatarduel.event;

import com.avatarduel.model.Game;
import com.avatarduel.model.type.PlayerType;

/**
 * DrawEvent is a event for drawing card from own deck.
 * Only valid in Draw Phase
 *
 * IMPORTANT NOTE:
 * This event will communicate with game singleton instantly, so there are no need to validate
 * In case where event is not possible to do, we throw exception so that the GUI Board can give the
 * error message to the player playing the games
 *
 * This event can only be used if there are more than 1 card left in the deck
 * @author G10-K03-CardGameOOP
 */

public class DrawEvent implements IEvent {
    private PlayerType playerType;
    public DrawEvent(PlayerType type) {
        this.playerType = type;
    }

    /**
     * execute method to run the event invoked by user action
     */
    @Override
    public void execute() {
        Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).draw();
        Game.getInstance().nextPhase();  // pindah ke next phase
    }

}
