package com.avatarduel.factory;

import com.avatarduel.model.card.*;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;

/**
 * CardFactory is a factory method design pattern implementation for creating cards from database.
 * IMPORTANT NOTE:
 * This factory also used to creating clone of card object, so we can seperate object from database and object that were used in the game
 * @author G10-K03-CardGameOOP
 */

public class CardFactory {

    /**
     * Static method for outputing the element in the game
     * @param element : The string representation of element from database
     * @return Element : Game Element object representation
     */
    public static Element outputElement(String element) {
        switch (element) {
            case "WATER" : return Element.WATER;
            case "AIR" : return Element.AIR;
            case "EARTH" : return Element.EARTH;
            case "FIRE" : return Element.FIRE;
            default: return Element.ENERGY;
        }
    }

    /**
     * Method for cloning card object in the game
     * @param card : card to be cloned
     * @return CharacterCard : cloned object card
     */
    public static CharacterCard createClone(CharacterCard card) {
        return new CharacterCard(card.getId(), card.getName(), card.getElement(), card.getDescription(), card.getImage(), card.getAttack(),card.getDefense(), card.getPower());
    }

    /**
     * Method for cloning card object in the game
     * @param card : card to be cloned
     * @return SkillAuraCard : cloned object card
     */
    public static SkillAuraCard createClone(SkillAuraCard card) {
        return new SkillAuraCard(card.getId(), card.getName(), card.getElement(), card.getDescription(), card.getImage(), card.getPower(), card.getAttack(),card.getDefense());
    }

    /**
     * Method for cloning card object in the game
     * @param card : card to be cloned
     * @return LandCard : cloned object card
     */
    public static LandCard createClone (LandCard card) {
        return new LandCard(card.getId(), card.getName(), card.getElement(), card.getDescription(), card.getImage());
    }

    /**
     * Method for cloning card object in the game
     * @param card : card to be cloned
     * @return SkillDestroyCard : cloned object card
     */
    public static SkillDestroyCard createClone (SkillDestroyCard card) {
        return new SkillDestroyCard(card.getId(), card.getName(), card.getElement(), card.getDescription(), card.getImage(), String.valueOf(card.getPower()));
    }

    /**
     * Method for cloning card object in the game
     * @param card : card to be cloned
     * @return SkillPowerUpCard : cloned object card
     */
    public static SkillPowerUpCard createClone (SkillPowerUpCard card) {
        return new SkillPowerUpCard(card.getId(), card.getName(), card.getElement(), card.getDescription(), card.getImage(), String.valueOf(card.getPower()));
    }

    /**
     * Method for cloning card object in the game
     * @param components : csv data
     * @param type : Card Type to be produced into game object
     * @return CharacterCard : cloned object card
     */
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
