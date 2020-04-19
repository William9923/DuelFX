package com.avatarduel.model.card;

import com.avatarduel.model.type.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterCardTest {
    private CharacterCard CC;

    public CharacterCardTest() {
        this.CC = new CharacterCard(0, "sample name", Element.AIR, "lorem ipsum", "imgfile", 99, 100, 101);
    }

    @Test
    public void getAttack() {
        assertEquals(99, CC.getAttack());
    }

    @Test
    public void getDefense() {
        assertEquals(100, CC.getDefense());
    }

    @Test
    public void getPower() {
        assertEquals(101, CC.getPower());
    }

    @Test
    public void setAttack() {
        CC.setAttack(9999);
        assertEquals(9999, CC.getAttack());
    }

    @Test
    public void setDefense() {
        CC.setDefense(9999);
        assertEquals(9999, CC.getDefense());
    }

    @Test
    public void setPower() {
        CC.setPower(9999);
        assertEquals(9999, CC.getPower());
    }
}
