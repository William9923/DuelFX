package com.avatarduel.model.card;
import com.avatarduel.model.type.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SkillAuraCardTest {
    private SkillAuraCard Card;

    public SkillAuraCardTest() {
        this.Card = new SkillAuraCard(0, "sample name", Element.WATER, "lorem ipsum",
                "imgfile", 99, 100, 101);
    }

    @Test
    public void getDefense() {
        assertEquals(101, Card.getDefense());
    }

    @Test
    public void setDefense() {
        Card.setDefense(999);
        assertEquals(999, Card.getDefense());
    }

    @Test
    public void getAttack() {
        assertEquals(100, Card.getAttack());
    }

    @Test
    public void setAttack() {
        Card.setAttack(999);
        assertEquals(999, Card.getAttack());
    }
}
