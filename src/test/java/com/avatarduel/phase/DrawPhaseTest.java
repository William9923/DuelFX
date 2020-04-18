package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawPhaseTest {

    private Phase phase1;

    public DrawPhaseTest(){ this.phase1 = new DrawPhaseTest(); }

    @Test
    void getPhase() { assertEquals(DrawPhase, DrawPhase.getPhase()); }

    @Test
    void next() { assertEquals(MainPhase, DrawPhase.next()); }
}