package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public class BattlePhase implements IPhase {

    private Phase phase;

    public BattlePhase() {
        phase = Phase.BATTLE;
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
