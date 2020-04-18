package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public class MainPhase implements IPhase {

    private Phase phase;

    //Constructor for MainPhase
    public MainPhase() {
        phase = Phase.MAIN;
    }

    //Method to return current Phase
    @Override
    public Phase getPhase() {
        return phase;
    }

    //Method to get to the next phase
    @Override
    public IPhase next() {
        return new BattlePhase();
    }
}
