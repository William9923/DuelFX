package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;

/**
 * Interface for Phase Object in Game
 * Contains 2 method :
 * 1. getPhase
 * 2. next
 *
 * @author  G10-K03-CardGameOOP
 */
public interface IPhase {
    public Phase getPhase();
    public IPhase next();
}
