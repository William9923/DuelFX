package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public class MainPhase1 implements IPhase {

    private Phase phase;

    public MainPhase1() {
        phase = Phase.MAIN1;
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
