package com.avatarduel.factory;

import com.avatarduel.model.*;

import java.util.List;

public class CardFactory {

    private int idCard = 0;

    public Card createCard(String[] components, CardType type) {
        if (type.equals(CardType.CHARACTER)) { // character card
            idCard++;
            return new CharacterCard(String.valueOf(idCard), components[1], components[2], components[3], components[4], components[5], components[6], components[7]);
        }

        if (type.equals(CardType.LAND)) { // land card
            idCard++;
            return new LandCard(String.valueOf(idCard), components[1], components[2], components[3], components[4]);
        }

        if (type.equals(CardType.SKILL_AURA)) {
            idCard++;
            return new SkillAuraCard(String.valueOf(idCard), components[1], components[2],  components[3], components[4], components[5], components[6], components[7]);
        }

        if (type.equals(CardType.SKILL_DESTROY)) {
            idCard++;
            return new SkillDestroyCard(String.valueOf(idCard), components[1], components[2], components[3], components[4], components[5]);
        }

        if (type.equals(CardType.SKILL_POWER_UP)) {
            idCard++;
            return new SkillPowerUpCard(String.valueOf(idCard), components[1], components[2], components[3], components[4], components[5]);
        }

        return null;
    }
}
