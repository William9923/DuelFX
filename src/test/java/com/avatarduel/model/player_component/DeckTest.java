package com.avatarduel.model.player_component;

import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.PlayerType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeckTest {

    private Deck deck;

    public DeckTest() {
        this.deck = new Deck(60, PlayerType.A);
    }

    @Test
    public void drawTest() {
        deck.draw();
        assertEquals(60 - 1, deck.size());
    }

    @Test
    public void shuffleTest() {
        this.deck = new Deck(30, PlayerType.A);


        long countSkill = deck.stream()
                .filter(c -> c.getType() == CardType.SKILL_AURA || c.getType() == CardType.SKILL_DESTROY || c.getType() == CardType.SKILL_POWER_UP)
                .count();

        long countCharacter = deck.stream()
                .filter(c -> c.getType() == CardType.CHARACTER)
                .count();

        long countLand = deck.stream()
                .filter(c -> c.getType() == CardType.LAND)
                .count();
        deck.shuffle();

        long countSkill2 = deck.stream()
                .filter(c -> c.getType() == CardType.SKILL_AURA || c.getType() == CardType.SKILL_DESTROY || c.getType() == CardType.SKILL_POWER_UP)
                .count();

        long countCharacter2 = deck.stream()
                .filter(c -> c.getType() == CardType.CHARACTER)
                .count();

        long countLand2 = deck.stream()
                .filter(c -> c.getType() == CardType.LAND)
                .count();

        assertEquals(countCharacter, countCharacter2);
        assertEquals(countLand,countLand2);
        assertEquals(countSkill, countSkill2);
        assertEquals(30, deck.size());
    }
}