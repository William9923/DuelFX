package com.avatarduel.factory;

import com.avatarduel.model.card.*;
import com.avatarduel.model.type.CharacterState;

/**
 * CardFactoryInField is a factory method design pattern implementation for creating cards that implement the adapter pattern for adapting from Normal Card into Card in the field.
 * IMPORTANT NOTE:
 * @author G10-K03-CardGameOOP
 */

public class CardInFieldFactory {

    /**
     *
     * @param card : The original card
     * @param createdAt : Created at which turn
     * @param index : index on the Field (where to put the card)
     * @param state : Character State (null if it is skill card)
     * @return IField : Adapter for card that in the field
     */
    public IField createCardInField(Card card, int createdAt, int index, CharacterState state) {
        switch (card.getType()) {
            case CHARACTER:
                return new CharacterCardInField((CharacterCard) card, state, createdAt, index);
            case SKILL_POWER_UP:
            case SKILL_AURA:
                return new SkillCardInField((SkillCard) card, createdAt, index);
        }
        return null;
    }
}
