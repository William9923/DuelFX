package com.avatarduel.model.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardModelTest {
    private Card CardTest;

    public CardModelTest() {
      this.CardTest = new Card(99, "Sample", WATER, "Sample Description", "image sample");
    }

    @Test
    public Element getElement() {
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
    public setAndGetType() {
      CardTest.setType("Sample Type");
      assertEquals(CardTest.getType(), "Sample Type");
    }
}
