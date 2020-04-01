package com.avatarduel.factory;

import com.avatarduel.model.*;
import com.avatarduel.type.CardType;

public class CardFactory {

    public Card createCard(String[] components, CardType type) {
        if (type.equals(CardType.CHARACTER)) { // character card
            return new CharacterCard(components[0], components[1], components[2], components[3], components[4], components[5], components[6], components[7]);
        }

        if (type.equals(CardType.LAND)) { // land card
            return new LandCard(components[0], components[1], components[2], components[3], components[4]);
        }

        if (type.equals(CardType.SKILL_AURA)) {
            return new SkillAuraCard(components[0], components[1], components[2],  components[3], components[4], components[5], components[6], components[7]);
        }

        if (type.equals(CardType.SKILL_DESTROY)) {
            return new SkillDestroyCard(components[0], components[1], components[2], components[3], components[4], components[5]);
        }

        if (type.equals(CardType.SKILL_POWER_UP)){
            return new SkillPowerUpCard(components[0], components[1], components[2], components[3], components[4], components[5]);
        }

        return null;
    }
}
