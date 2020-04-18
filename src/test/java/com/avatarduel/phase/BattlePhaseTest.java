package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class BattlePhaseTest {

    private BattlePhase battlePhase;

    public BattlePhaseTest(){ this.battlePhase = new BattlePhase(); }

    @Test
    void getPhase() { assertEquals(battlePhase.getPhase(), Phase.BATTLE); }

    @Test
    void next() { assertTrue(EqualsBuilder.reflectionEquals(battlePhase.next(), new EndPhase())); }
}
