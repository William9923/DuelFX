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

        if (type.equals(CardType.SKILL)) {
            if (components.length == 8) { // aura card
                idCard++;
                return new SkillCard(String.valueOf(idCard), components[1], components[2], components[3], components[4], components[5], components[6], components[7]);
            }

            if (components.length == 7) { // power up card
                idCard++;
                return new SkillCard(String.valueOf(idCard), components[1], components[2], components[3], components[4], components[5], components[6]);
            }

            if (components.length == 6) { // destroy card
                idCard++;
                return new SkillCard(String.valueOf(idCard), components[1], components[2], components[3], components[4], components[5], components[6], components[7]);
            }

        }

        return null;
    }
}
