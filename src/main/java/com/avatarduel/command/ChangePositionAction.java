package com.avatarduel.command;

import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.player_component.Field;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class ChangePositionAction  implements IAction{

    private CharacterCardInField character;
    private PlayerType p;

    public ChangePositionAction(PlayerType p, CharacterCardInField character) {
        this.p = p;
        this.character = character;

        if (validate()) {
            execute();
        } // else : throw error

    }

    @Override
    public void execute() {
        character.changeState();
    }

    @Override
    public boolean validate() {
        Field f1 = Game.getInstance().getPlayerByType(p).getField();
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();

        return !(!(currPhase.equals(Phase.MAIN1) || currPhase.equals(Phase.MAIN2)) || !currPlayer.equals(p));

    }
}
