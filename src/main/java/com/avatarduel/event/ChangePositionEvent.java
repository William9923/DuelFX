package com.avatarduel.event;

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
    public void execute() {
        Game.getInstance().getPlayerByType(p).getField().getCharacterCardByID(characterId).switchPosition();
    }

    @Override
    public boolean validate() {
        CharacterCardInField card = Game.getInstance().getPlayerByType(p).getField().getCharacterCardByID(characterId);
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        return (currPhase.equals(Phase.MAIN1) || currPhase.equals(Phase.MAIN2)) && card != null && !card.hasAttacked && currPlayer == p;
    }
}
