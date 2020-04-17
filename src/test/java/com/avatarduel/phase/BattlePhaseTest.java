package com.avatarduel.phase;

import com.avatarduel.event.NextPhaseEvent;
import com.avatarduel.model.type.Phase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattlePhaseTest {

    private Phase phase1;

    public BattlePhaseTest(){ this.phase1 = new BattlePhaseTest(); }

    @Test
    void getPhase() { assertEquals(BattlePhase, BattlePhase.getPhase()); }

    @Test
    void next() { assertEquals(EndPhase, BattlePhase.next()); }
}