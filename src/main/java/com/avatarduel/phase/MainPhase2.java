package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public class MainPhase2 implements IPhase {

    private Phase phase;

    public MainPhase2() {
        phase = Phase.MAIN2;
    }
    @Override
    public Phase getPhase() {
        return phase;
    }

    @Override
    public IPhase next() {
        return new EndPhase();
    }
}
