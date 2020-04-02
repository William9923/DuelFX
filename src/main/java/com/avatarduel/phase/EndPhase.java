package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public class EndPhase implements IPhase {

    private Phase phase;

    public EndPhase() {
        phase = Phase.END;
    }
    @Override
    public Phase getPhase() {
        return phase;
    }

    @Override
    public IPhase next() {
        return new DrawPhase();
    }
}
