package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class MainPhaseTest {

    private MainPhase mainPhase;

    public MainPhaseTest (){ this.mainPhase = new MainPhase(); }
    @Test
    void getPhase() { assertEquals(Phase.MAIN, mainPhase.getPhase()); }

    @Test
    void next() { assertEquals(Phase.BATTLE, mainPhase.next().getPhase()); }
}