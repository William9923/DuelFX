package com.avatarduel.factory;

import com.avatarduel.model.*;

import java.util.List;

public class CardFactory {
    public Card createCard(String[] components, CardType type) {
        if (type.equals(CardType.CHARACTER)) { // character card
            System.out.println(components.length);
            for (String component: components) {
                System.out.println(component);
            }
            return new CharacterCard(components[0], components[1], components[2], components[3], components[4], components[5], components[6], components[7]);
        }

        if (type.equals(CardType.LAND)) { // land card
            return new LandCard(components[0], components[1], components[2], components[3], components[4]);
        }

        if (type.equals(CardType.SKILL)) {
            if (components.length == 8) { // aura card
                return new SkillCard(components[0], components[1], components[2], components[3], components[4], components[5], components[6], components[7]);
            }

            if (components.length == 7) { // power up card
                return new SkillCard(components[0], components[1], components[2], components[3], components[4], components[5], components[6]);
            }

            if (components.length == 6) { // destroy card
                return new SkillCard(components[0], components[1], components[2], components[3], components[4], components[5], components[6], components[7]);
            }

        }

        return null;
    }
}
