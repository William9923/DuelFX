package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public class EndPhase implements IPhase {

    private Phase phase;

    //Constructor for EndPhase
    public EndPhase() {
        phase = Phase.END;
    }

    //Method to return current Phase
    @Override
    public Phase getPhase() {
        return phase;
    }

    //Method to get to the next phase
    @Override
    public IPhase next() {
        return new DrawPhase();
    }
}
