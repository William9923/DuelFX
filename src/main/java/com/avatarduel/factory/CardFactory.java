package com.avatarduel.factory;

import com.avatarduel.model.*;
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
            default: return Element.ENERGY; // blom di handle
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
        switch (type) {
            case CHARACTER:  // character card
                return new CharacterCard(components);

            case LAND: // land card
                return new LandCard(components);

            case SKILL_AURA:
                return new SkillAuraCard(components);

            case SKILL_DESTROY:
                return new SkillDestroyCard(components);

            case SKILL_POWER_UP:
                return new SkillPowerUpCard(components);
        }

        return null;
    }
}
