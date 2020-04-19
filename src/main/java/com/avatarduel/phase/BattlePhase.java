package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

/**
 * BattlePhase is a representation of battle phase in the game.
 * @author G10-K03-CardGameOOP
 */

public class BattlePhase implements IPhase {

    private Phase phase;

    /**
     * BattlePhase Constructor
     */
    public BattlePhase() {
        phase = Phase.BATTLE;
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
        return new EndPhase();
    }
}
