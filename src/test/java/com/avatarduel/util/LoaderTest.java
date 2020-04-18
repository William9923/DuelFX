package com.avatarduel.util;

import com.avatarduel.model.*;
import com.avatarduel.model.card.*;
import com.avatarduel.model.type.Element;
import org.junit.jupiter.api.Test;
import static  org.junit.Assert.assertEquals;

import java.util.List;

class LoaderTest {
    private final Loader loader = new Loader();

    @Test
    void loadCards() {
        try {
            List<String[]> characters = loader.loadCards(Loader.CHARACTER_CSV_FILE_PATH);
            List<String[]> lands = loader.loadCards(Loader.LAND_CSV_FILE_PATH);
            List<String[]> skillAuras = loader.loadCards(Loader.SKILL_AURA_CSV_FILE_PATH);
            List<String[]> skillDestroys = loader.loadCards(Loader.SKILL_DESTROY_CSV_FILE_PATH);
            List<String[]> skillPowerUps = loader.loadCards(Loader.SKILL_POWER_UP_CSV_FILE_PATH);
            assertEquals(characters.get(0)[0], "17");
            assertEquals(lands.get(0)[0], "1");
            assertEquals(skillAuras.get(0)[0], "65");
            assertEquals(skillDestroys.get(0)[0], "22");
            assertEquals(skillPowerUps.get(0)[0], "14");
        }
        catch(Exception e) {
            assert(false);
        }
    }

    @Test
    void loadLand() {
        List<Card> landCards = loader.loadLand();
        LandCard shouldBeEasternAirTemple = (LandCard) landCards.get(0);
        // TODO : 1	Eastern Air Temple	AIR	One of the two temples exclusively housing female airbenders.	src/res/image/land/Eastern Air Temple.png

        assertEquals(shouldBeEasternAirTemple.getName(), "Eastern Air Temple");
        assertEquals(shouldBeEasternAirTemple.getElement(), Element.AIR);
        assertEquals(shouldBeEasternAirTemple.getImage(), "src/res/image/land/Eastern Air Temple.png");
    }

    @Test
    void loadCharacter() {
        List<Card> characterCards = loader.loadCharacter();
        CharacterCard shouldBeKatara = (CharacterCard) characterCards.get(0);
        //17	Katara	WATER	Waterbending master from Southern Water Tribe, sister of Sokka, and friend of Aang.	src/res/image/character/Katara.png	13	7	3

        assertEquals(shouldBeKatara.getName(), "Katara");
        assertEquals(shouldBeKatara.getElement(), Element.WATER);
        assertEquals(shouldBeKatara.getImage(), "src/res/image/character/Katara.png");
        assertEquals(shouldBeKatara.getAttack(), 13);
        assertEquals(shouldBeKatara.getDefense(), 7);
        assertEquals(shouldBeKatara.getPower(), 3);
    }

    @Test
    void loadSkillAura() {
        List<Card> skillAuraCards = loader.loadSkillAura();
        SkillAuraCard shouldBeAirFunnel = (SkillAuraCard) skillAuraCards.get(0);
        //65	Air Funnel	AIR	Technique to create a cannon for small projectiles.	src/res/image/skill/Air Funnel.png	1	4	0

        assertEquals(shouldBeAirFunnel.getName(), "Air Funnel");
        assertEquals(shouldBeAirFunnel.getElement(), Element.AIR);
        assertEquals(shouldBeAirFunnel.getImage(), "src/res/image/skill/Air Funnel.png");
        assertEquals(shouldBeAirFunnel.getAttack(), 4);
        assertEquals(shouldBeAirFunnel.getDefense(), 0);
        assertEquals(shouldBeAirFunnel.getPower(), 1);
    }

    @Test
    void loadSkillDestroy() {
        List<Card> skillAuraCards = loader.loadSkillAura();
        SkillAuraCard shouldBeAirFunnel = (SkillAuraCard) skillAuraCards.get(0);
        //65	Air Funnel	AIR	Technique to create a cannon for small projectiles.	src/res/image/skill/Air Funnel.png	1	4	0

        assertEquals(shouldBeAirFunnel.getName(), "Air Funnel");
        assertEquals(shouldBeAirFunnel.getElement(), Element.AIR);
        assertEquals(shouldBeAirFunnel.getImage(), "src/res/image/skill/Air Funnel.png");
        assertEquals(shouldBeAirFunnel.getAttack(), 4);
        assertEquals(shouldBeAirFunnel.getDefense(), 0);
        assertEquals(shouldBeAirFunnel.getPower(), 1);
    }

    @Test
    void loadSkillPowerUp() {
        List<Card> powerUpCards = loader.loadSkillPowerUp();
        SkillPowerUpCard shouldBeBlackFlame = (SkillPowerUpCard) powerUpCards.get(0);
        //65	Air Funnel	AIR	Technique to create a cannon for small projectiles.	src/res/image/skill/Air Funnel.png	1	4	0

        assertEquals(shouldBeBlackFlame.getName(), "Black Flame");
        assertEquals(shouldBeBlackFlame.getElement(), Element.FIRE);
        assertEquals(shouldBeBlackFlame.getImage(), "src/res/image/skill/black flame.png");
        assertEquals(shouldBeBlackFlame.getPower(), 1);
    }
}