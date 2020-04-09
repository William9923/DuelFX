package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public class MainPhase implements IPhase {

    private Phase phase;

    public MainPhase() {
        phase = Phase.MAIN;
    }
    @Override
    public Phase getPhase() {
        return phase;
    }

    @Override
    public IPhase next() {
        return new BattlePhase();
    }
}
