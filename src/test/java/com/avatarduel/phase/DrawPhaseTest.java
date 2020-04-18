package com.avatarduel.phase;

import com.avatarduel.model.type.Phase;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawPhaseTest {

    private DrawPhase drawPhase;

    public DrawPhaseTest(){ this.drawPhase = new DrawPhase(); }

    @Test
    void getPhase() { assertEquals(drawPhase.getPhase(), Phase.DRAW); }

    @Test
    void next() { assertTrue(EqualsBuilder.reflectionEquals(new MainPhase(), drawPhase.next())); }
}