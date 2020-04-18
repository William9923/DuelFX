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
      assertEquals(WATER, CardTest.getElement());
    }

    @Test
    public int getId() {
      assertEquals(99, CardTest.getId());
    }

    @Test
    public String getDescription() {
      assertEquals("Sample Description", CardTest.getDescription());
    }

    @Test
    public String getImage() {
      assertEquals("image sample", CardTest.getImage());
    }

    // ...
}
