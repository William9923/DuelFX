package com.avatarduel.guicontroller.Request.SpecificRequest;

import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;

public class AttackRequest extends SpecificPlayerRequest {
    private CharacterCardInField attacker;
    public AttackRequest(CharacterCardInField attacker) {
        super(Game.getInstance().getCurrentPlayer());
        this.attacker = attacker;
    }

    public CharacterCardInField getAttacker() {
        return attacker;
    }
}
