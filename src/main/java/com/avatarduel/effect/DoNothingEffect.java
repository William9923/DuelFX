package com.avatarduel.effect;

import com.avatarduel.model.CharacterCard;
import com.avatarduel.model.CharacterCardInField;

public class DoNothingEffect implements IEffect{

    @Override
    public void showEffect() {
        // do nothing
        System.out.println("Wow nothing happened ..");
    }
}
