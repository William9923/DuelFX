package com.avatarduel.command;

import com.avatarduel.model.Game;
import com.avatarduel.model.player_component.Field;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class ChangePositionAction  implements ICommand, IValidate{

    private int charIdx;
    private PlayerType p;

//    public ChangePositionAction(PlayerType p, int characterIndex) {
//        this.p = p;
//        this.charIdx = characterIndex;
//
//        if (validate()) {
//            execute();
//        } // else : throw error
//
//    }

    @Override
    public void execute() {
        //
    }

    @Override
    public boolean validate() {
//        Field f1 = Game.getInstance().getPlayerByType(p).getField();
//        Phase currPhase = Game.getInstance().getCurrentPhase();
    return false;

    }
}
