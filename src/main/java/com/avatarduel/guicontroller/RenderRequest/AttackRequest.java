package com.avatarduel.guicontroller.RenderRequest;

import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.PlayerType;

public class AttackRequest extends RenderRequest{
    private CharacterCardInField attacker;
    public AttackRequest(CharacterCardInField attacker) {
        super(Game.getInstance().getCurrentPlayer());
        this.attacker = attacker;
    }

    public CharacterCardInField getAttacker() {
        return attacker;
    }
}
