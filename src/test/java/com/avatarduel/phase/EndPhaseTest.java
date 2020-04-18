package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class EndPhaseTest {

    private EndPhase endPhase;

    public EndPhaseTest (){ this.endPhase = new EndPhase(); }
    @Test
    void getPhase() { assertEquals(Phase.END, endPhase.getPhase()) ;}

    @Test
    void next() { assertEquals(Phase.DRAW, endPhase.next().getPhase()); }
}