package com.avatarduel.model.card;
import com.avatarduel.model.type.Element;
import com.avatarduel.model.type.CardType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private Card CardTest;

    public CardTest() {
        this.CardTest = new Card(99, "Sample", Element.WATER, "Sample Description", "image sample");
    }

    @Test
    public void getElement() {
        assertEquals(Element.WATER, CardTest.getElement());
    }

    @Test
    public void getId() {
        assertEquals(99, CardTest.getId());
    }

    @Test
    public void getDescription() {
        assertEquals("Sample Description", CardTest.getDescription());
    }

    @Test
    public void getImage() {
        assertEquals("image sample", CardTest.getImage());
    }

    @Test
    public void getName() {
        assertEquals("Sample", CardTest.getName());
    }

    @Test
    public void setDescription() {
        CardTest.setDescription("lorem ipsum");
        assertEquals(CardTest.getDescription(), "lorem ipsum");
    }

    @Test
    public void setId() {
        CardTest.setId(0);
        assertEquals(CardTest.getId(), 0);
    }

    @Test
    public void setImage() {
        CardTest.setImage("sample pic");
        assertEquals(CardTest.getImage(), "sample pic");
    }

    @Test
    public void setName() {
        CardTest.setName("sample");
        assertEquals(CardTest.getName(), "sample");
    }

    @Test
    public void setAndGetPower() {
        CardTest.setPower(0);
        assertEquals(CardTest.getPower(), 0);
    }

    @Test
    public void setAndGetType() {
        CardTest.setType(CardType.CHARACTER);
        assertEquals(CardTest.getType(), CardType.CHARACTER);
    }
}

