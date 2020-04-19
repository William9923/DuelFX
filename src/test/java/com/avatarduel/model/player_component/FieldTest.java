package com.avatarduel.model.player_component;

import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillAuraCard;
import com.avatarduel.model.card.SkillCard;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldTest {

    private Field field;

    public FieldTest() {
    }

    @Test
    public void testAddAndRemoveCharacter() {
        this.field = new Field(5);
        CharacterCard card1 = new CharacterCard(0, "sample name", Element.AIR, "lorem ipsum", "imgfile", 99, 100, 101);
        CharacterCard card2 = new CharacterCard(1, "sample name", Element.AIR, "lorem ipsum", "imgfile", 99, 100, 101);

        CharacterCardInField char1 = new CharacterCardInField(card1, CharacterState.ATTACK, 1,0);
        CharacterCardInField char2 = new CharacterCardInField(card2, CharacterState.ATTACK, 1,1);

        this.field.addCharacterCard(char1);
        this.field.addCharacterCard(char2);

        assertEquals(2, field.getCharCardList().size());

        this.field.removeCharacterCard(char2);

        assertEquals(1, this.field.getCharCardList().size());

    }

    @Test
    public void testGetCharacterByID() {
        this.field = new Field(5);
        CharacterCard card1 = new CharacterCard(0, "sample name", Element.AIR, "lorem ipsum", "imgfile", 99, 100, 101);
        CharacterCardInField char1 = new CharacterCardInField(card1, CharacterState.ATTACK, 1,0);

        this.field.addCharacterCard(char1);

        CharacterCardInField card = this.field.getCharacterCardByID(0);

        assertEquals(char1, card);
    }

    @Test
    public void testAddSkillCard() {
        this.field = new Field(5);
        SkillCard card = new SkillAuraCard(0, "sample name", Element.WATER, "lorem ipsum",
                "imgfile", 99, 100, 101);

        this.field.addSkillCard(card,0,1);
        assertEquals(1, field.getSkillCardList().size());

    }

    @Test
    public void testGetEmptyCharacterIndex(){

        this.field = new Field(5);

        assertEquals(0, this.field.getEmptyCharacterIndex());
        CharacterCard card1 = new CharacterCard(0, "sample name", Element.AIR, "lorem ipsum", "imgfile", 99, 100, 101);
        CharacterCardInField char1 = new CharacterCardInField(card1, CharacterState.ATTACK, 1,0);

        this.field.addCharacterCard(char1);

        assertEquals(1, this.field.getEmptyCharacterIndex());
    }

    @Test
    public void testGetEmptySkillIndex() {
        this.field = new Field(5);

        assertEquals(0, this.field.getEmptySkillCardIndex());

        SkillCard card = new SkillAuraCard(0, "sample name", Element.WATER, "lorem ipsum",
                "imgfile", 99, 100, 101);

        this.field.addSkillCard(card,0,1);

        assertEquals(1, this.field.getEmptySkillCardIndex());

    }


}