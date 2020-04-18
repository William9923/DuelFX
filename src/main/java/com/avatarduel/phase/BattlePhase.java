package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public class BattlePhase implements IPhase {

    private Phase phase;

    //Constructor for BattlePhase
    public BattlePhase() {
        phase = Phase.BATTLE;
    }

    //Method to return current Phase
    @Override
    public Phase getPhase() {
        return phase;
    }

    //Method to get to the next phase
    @Override
    public IPhase next() {
        return new EndPhase();
    }
}
