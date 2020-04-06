package com.avatarduel.factory;

import com.avatarduel.model.card.*;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;

public class CardInFieldFactory {
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
