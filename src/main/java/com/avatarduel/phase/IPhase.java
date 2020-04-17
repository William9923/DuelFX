package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

public interface IPhase {

    public Phase getPhase();
    //Interface for antoher phases class

    public IPhase next();
    //Interface for another phases class
}
