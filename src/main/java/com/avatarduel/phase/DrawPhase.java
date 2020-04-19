package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

/**
 * DrawPhase is a representation of end phase in the game.
 * IMPORTANT NOTE:
 * Player are not going to play in this phase.
 * This phase only a representation to refresh the game state and draw from the deck
 * @author G10-K03-CardGameOOP
 */


public class DrawPhase implements IPhase{

    private Phase phase;

    /**
     * DrawPhase Constructor
     */
    public DrawPhase() {
        phase = Phase.DRAW;
    }

    /**
     * Get the Phase Enum
     * @return Phase Object Representation
     */
    @Override
    public Phase getPhase() {
        return phase;
    }

    /**
     * Change the phase into next phase using state design pattern
     * @return IPhase -> representation of next phase
     */
    @Override
    public IPhase next() {
        return new MainPhase();
    }
}
