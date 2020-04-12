package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;
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
        CharacterCardInField card = Game.getInstance().getPlayerByType(p).getField().getCharacterCardByID(characterId);
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();

        if (currPhase != Phase.MAIN){
            throw new InvalidOperationException("Change Position Character", "Not in the Main Phase");
        }

        if (card.hasAttacked){
            throw new InvalidOperationException("Change Position Character", "Cannot change position after a attack");
        }

        if (currPlayer!=p){
            throw new InvalidOperationException("Change Position Character", "Invalid Turn!");
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
