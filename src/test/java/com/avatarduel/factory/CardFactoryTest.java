package com.avatarduel.factory;

import com.avatarduel.model.*;
import com.avatarduel.model.card.*;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;
import com.avatarduel.util.Loader;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {
    private CardFactory cardFactory;

    public CardFactoryTest() {
        this.cardFactory = new CardFactory();
    }

    @Test
    void outputElement() {
        assertEquals(CardFactory.outputElement("FIRE"), Element.FIRE);
        assertEquals(CardFactory.outputElement("WATER"), Element.WATER);
        assertEquals(CardFactory.outputElement("EARTH"), Element.EARTH);
        assertEquals(CardFactory.outputElement("AIR"), Element.AIR);
        assertEquals(CardFactory.outputElement("ENERGY"), Element.ENERGY);
    }

    @Test
    void testCreateClone1() {
        LandCard landCard = new LandCard(1,"mario",Element.FIRE,"ichmemario","mariobros.jpg");
        assertTrue(EqualsBuilder.reflectionEquals(landCard, CardFactory.createClone(landCard)));
    }

    @Test
    void testCreateClone2() {
        CharacterCard characterCard = new CharacterCard(1,"mario",Element.FIRE,"ichmemario","mariobros.jpg",1,2,3);
        assertTrue(EqualsBuilder.reflectionEquals(characterCard, CardFactory.createClone(characterCard)));
    }

    @Test
    void testCreateClone3() {
        SkillAuraCard skillAuraCard = new SkillAuraCard(1,"mario",Element.FIRE,"ichmemario","mariobros.jpg",1,2,3);
        assertTrue(EqualsBuilder.reflectionEquals(skillAuraCard, CardFactory.createClone(skillAuraCard)));
    }

    @Test
    void testCreateClone4() {
        SkillDestroyCard skillDestroyCard = new SkillDestroyCard(1,"mario",Element.FIRE,"ichmemario","mariobros.jpg","2");
        assertTrue(EqualsBuilder.reflectionEquals(skillDestroyCard, CardFactory.createClone(skillDestroyCard)));
    }

    @Test
    void testCreateClone5() {
        SkillPowerUpCard skillPowerUpCard = new SkillPowerUpCard(1,"mario",Element.FIRE,"ichmem","aaa.png","2");
        assertTrue(EqualsBuilder.reflectionEquals(skillPowerUpCard, CardFactory.createClone(skillPowerUpCard)));

    }

    @Test
    void createCard() {
        try {
            Loader loader = new Loader();
            List<String[]> characters = loader.loadCards(Loader.CHARACTER_CSV_FILE_PATH);
            List<String[]> skillAuras = loader.loadCards(Loader.SKILL_AURA_CSV_FILE_PATH);
            List<String[]> skillPowerups = loader.loadCards(Loader.SKILL_POWER_UP_CSV_FILE_PATH);
            List<String[]> skillDestroys = loader.loadCards(Loader.SKILL_DESTROY_CSV_FILE_PATH);
            List<String[]> lands = loader.loadCards(Loader.LAND_CSV_FILE_PATH);
            for(int i = 0 ; i < 1 ; i++) {
                assertTrue(EqualsBuilder.reflectionEquals(new CharacterCard(characters.get(i)),  cardFactory.createCard(characters.get(i), CardType.CHARACTER)));
                assertTrue(EqualsBuilder.reflectionEquals(new SkillAuraCard(skillAuras.get(i)),  cardFactory.createCard(skillAuras.get(i), CardType.SKILL_AURA)));
                assertTrue(EqualsBuilder.reflectionEquals(new SkillDestroyCard(skillDestroys.get(i)),  cardFactory.createCard(skillDestroys.get(i), CardType.SKILL_DESTROY)));
                assertTrue(EqualsBuilder.reflectionEquals(new SkillPowerUpCard(skillPowerups.get(i)),  cardFactory.createCard(skillPowerups.get(i), CardType.SKILL_POWER_UP)));
                assertTrue(EqualsBuilder.reflectionEquals(new LandCard(lands.get(i)),  cardFactory.createCard(lands.get(i), CardType.LAND)));
            }
        }
        catch(IOException | URISyntaxException e) {
            System.out.println("Loader masih error");
        }
    }
}