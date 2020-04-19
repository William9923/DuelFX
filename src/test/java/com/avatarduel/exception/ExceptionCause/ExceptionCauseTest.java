package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;
import com.avatarduel.model.type.Phase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionCauseTest {
    private ExceptionCause exceptionCause;
    @Test
    public void testNullCauses() {
        try {
            exceptionCause = new AttackOnTheCreatedTurnCause();
            assertEquals("cannot attack on the same turn this character is created", exceptionCause.getCause());
            exceptionCause = new FullBoardCause(CardType.CHARACTER);
            assertEquals("full character field", exceptionCause.getCause());
            exceptionCause = new InvalidPhaseCause(Phase.MAIN);
            assertEquals("you can only do the action in phase main", exceptionCause.getCause());
            exceptionCause = new InvalidTargetCause(CardType.SKILL_AURA);
            assertEquals("skill aura cannot be used to the target", exceptionCause.getCause());
            exceptionCause = new MultipleAttackOnTheSameTurnCause();
            assertEquals("cannot attack twice on the same turn", exceptionCause.getCause());
            exceptionCause = new MultipleLandCardPlayedOnTheSameTurnCause();
            assertEquals("you can only play one land card per turn", exceptionCause.getCause());
            exceptionCause = new NoCharacterCardInFieldCause(CardType.SKILL_DESTROY);
            assertEquals("cannot play skill destroy because there are no character card in field", exceptionCause.getCause());
            exceptionCause = new NoCharacterCardToDestroyCause();
            assertEquals("enemy has no character card to destroy", exceptionCause.getCause());
            exceptionCause = new NotEnoughPowerCause(Element.FIRE);
            assertEquals("not enough fire element power", exceptionCause.getCause());
        }
        catch (NullPointerException nullPointerException) {
            assertTrue(false);
        }
    }
}