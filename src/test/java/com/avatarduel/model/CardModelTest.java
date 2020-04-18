package com.avatarduel.model;

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

    // ...
}
