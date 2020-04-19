package com.avatarduel.model.card;
import com.avatarduel.model.type.Element;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.CardType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterCardInFieldTest {
    private CharacterCardInField CCinField;

    public CharacterCardInFieldTest() {
        CharacterState State = CharacterState.ATTACK;
        CharacterCard Temp = new CharacterCard(0, "sample name", Element.AIR, "lorem ipsum", "imgfile", 99, 100, 101);
        this.CCinField = new CharacterCardInField(Temp, State, 999, 888);
    }

    @Test
    public void canAttack() {
        assertEquals(true, CCinField.canAttack());
    }

    @Test
    public void getIndex() {
        assertEquals(888, CCinField.getIndex());
    }

    @Test
    public void getCard() {
        assertEquals(0, CCinField.getCard().getId());
        assertEquals("sample name", CCinField.getCard().getName());
        assertEquals(Element.AIR, CCinField.getCard().getElement());
        assertEquals("lorem ipsum", CCinField.getCard().getDescription());
        assertEquals("imgfile", CCinField.getCard().getImage());
    }
}
