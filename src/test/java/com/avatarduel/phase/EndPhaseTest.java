package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EndPhaseTest {

    private Phase phase1;

    public EndPhaseTest (){ this.phase1 = new EndPhaseTest(); }
    @Test
    void getPhase() { assertEquals(EndPhase, EndPhase.getPhase()) ;}

    @Test
    void next() { assertEquals(DrawPhase, EndPhase.next()); }
}