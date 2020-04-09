package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public class DrawPhase  implements IPhase{

    private Phase phase;

    public DrawPhase() {
        phase = Phase.DRAW;
    }

    @Override
    public Phase getPhase() {
        return phase;
    }

    @Override
    public IPhase next() {
        return new MainPhase();
    }
}
