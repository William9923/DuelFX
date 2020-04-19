package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

/**
 * MainPhase is a representation of end phase in the game.
 * @author G10-K03-CardGameOOP
 */


public class MainPhase implements IPhase {

    private Phase phase;

    /**
     * MainPhase Constructor
     */
    public MainPhase() {
        phase = Phase.MAIN;
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
        return new BattlePhase();
    }
}
