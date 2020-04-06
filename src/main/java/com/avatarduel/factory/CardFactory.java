package com.avatarduel.factory;

import com.avatarduel.exception.InvalidCardException;
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
