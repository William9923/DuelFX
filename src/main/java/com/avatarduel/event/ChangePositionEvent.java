package com.avatarduel.event;

import com.avatarduel.exception.ExceptionCause.InvalidPhaseCause;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.exception.InvalidRotateException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class ChangePositionEvent implements IEvent {

    private int characterId;
    private PlayerType p;

    public ChangePositionEvent(PlayerType p, int id) {
        this.p = p;
        this.characterId = id;
    }

    @Override
    public void execute() throws InvalidOperationException {
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();

        if (currPhase != Phase.MAIN){
            throw new InvalidRotateException(new InvalidPhaseCause(Phase.MAIN));
        }

        Game.getInstance().getPlayerByType(p).getField().getCharacterCardByID(characterId).switchPosition();
    }

    @Override
    public boolean validate() {
        CharacterCardInField card = Game.getInstance().getPlayerByType(p).getField().getCharacterCardByID(characterId);
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        return (currPhase.equals(Phase.MAIN)) && card != null && !card.hasAttacked && currPlayer == p;
    }
}
