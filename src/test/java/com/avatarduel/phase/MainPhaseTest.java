package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainPhaseTest {

    private Phase phase1;

    public MainPhaseTest (){ this.phase1 = new MainPhase(); }
    @Test
    void getPhase() { assertEquals(MainPhase, MainPhase.getPhase()); }

    @Test
    void next() { assertEquals(BattlePhase, MainPhase.next()); }
}