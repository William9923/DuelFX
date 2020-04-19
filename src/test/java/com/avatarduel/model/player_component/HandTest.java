package com.avatarduel.model.player_component;


import com.avatarduel.model.card.Card;
import com.avatarduel.model.type.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    private Hand hand;

    public HandTest() {

    }

    @Test
    public void testAdd() {
        hand = new Hand(10);
        Card temp = new Card(0, "sample name", Element.AIR, "lorem ipsum", "imgfile");
        hand.add(temp);

        assertEquals(1, hand.size());

        for (int i = 0; i < 20; i++){
            hand.add(temp);
        }

        assertEquals(10, hand.size());
    }

}