package com.avatarduel.factory;

import com.avatarduel.model.card.*;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;

public class CardFactory {

    public static Element outputElement(String element) {
        switch (element) {
            case "WATER" : return Element.WATER;
            case "AIR" : return Element.AIR;
            case "EARTH" : return Element.EARTH;
            case "FIRE" : return Element.FIRE;
            default: return Element.AIR; // blom di handle
        }
    }
    public static CharacterCard createClone(CharacterCard card) {
        return new CharacterCard(card.getId(), card.getName(), card.getElement(), card.getDescription(), card.getImage(), card.getAttack(),card.getDefense(), card.getPower());
    }
    public static SkillAuraCard createClone(SkillAuraCard card) {
        return new SkillAuraCard(card.getId(), card.getName(), card.getElement(), card.getDescription(), card.getImage(), card.getPower(), card.getAttack(),card.getDefense());
    }

    public static LandCard createClone (LandCard card) {
        return new LandCard(card.getId(), card.getName(), card.getElement(), card.getDescription(), card.getImage());
    }

    public static SkillDestroyCard createClone (SkillDestroyCard card) {
        return new SkillDestroyCard(card.getId(), card.getName(), card.getElement(), card.getDescription(), card.getImage(), String.valueOf(card.getPower()));
    }
    public static SkillPowerUpCard createClone (SkillPowerUpCard card) {
        return new SkillPowerUpCard(card.getId(), card.getName(), card.getElement(), card.getDescription(), card.getImage(), String.valueOf(card.getPower()));
    }

    public Card createCard(String[] components, CardType type) {
        if (type.equals(CardType.CHARACTER)) { // character card
            return new CharacterCard(Integer.parseInt(components[0]), components[1], outputElement(components[2]), components[3], components[4], Integer.parseInt(components[5]), Integer.parseInt(components[6]), Integer.parseInt(components[7]));
        }

        if (type.equals(CardType.LAND)) { // land card
            return new LandCard(Integer.parseInt(components[0]), components[1], outputElement(components[2]), components[3], components[4]);
        }

        if (type.equals(CardType.SKILL_AURA)) {
            return new SkillAuraCard(Integer.parseInt(components[0]), components[1], outputElement(components[2]),  components[3], components[4], Integer.parseInt(components[5]), Integer.parseInt(components[6]), Integer.parseInt(components[7]));
        }

        if (type.equals(CardType.SKILL_DESTROY)) {
            return new SkillDestroyCard(Integer.parseInt(components[0]), components[1], outputElement(components[2]), components[3], components[4], components[5]);
        }

        if (type.equals(CardType.SKILL_POWER_UP)){
            return new SkillPowerUpCard(Integer.parseInt(components[0]), components[1], outputElement(components[2]), components[3], components[4], components[5]);
        }

        return null;
    }
}
