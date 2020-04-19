package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

/**
 * EndPhase is a representation of end phase in the game.
 * IMPORTANT NOTE:
 * Player are not going to play in this phase.
 * This phase only a representation to change the game state into other player
 * @author G10-K03-CardGameOOP
 */

public class EndPhase implements IPhase {

    private Phase phase;

    /**
     * EndPhase Constructor
     */
    public EndPhase() {
        phase = Phase.END;
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
        return new DrawPhase();
    }
}
