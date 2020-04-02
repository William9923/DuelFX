package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public interface IPhase {

    public Phase getPhase();

    public IPhase next();
}
