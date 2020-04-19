package com.avatarduel.event;

import com.avatarduel.exception.ExceptionCause.InvalidPhaseCause;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.exception.InvalidPhaseException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

/**
 * ChangePositionEvent is a event for changing current player position (ATTACK / DEFENSE).
 *
 * If the character targeted is in Attack Position, it will be switched to the Defense Position, vice versa
 * NOTE :
 * If the character being targeted by attack event in Attack Position, then the stats that counted were the Atk
 * While if the character being targeted by attack event in Defense Position, then the stats that counted were the Def  
 *
 * IMPORTANT NOTE:
 * This event will communicate with game singleton instantly, so there are no need to validate
 * In case where event is not possible to do, we throw exception so that the GUI Board can give the
 * error message to the player playing the games
 *
 * @author G10-K03-CardGameOOP
 */

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
            throw new InvalidPhaseException(new InvalidPhaseCause(card.getCard().getType()));
        }

        Game.getInstance().getPlayerByType(p).getField().getCharacterCardByID(characterId).switchPosition();
    }

//    @Override
//    public boolean validate() {
//        CharacterCardInField card = Game.getInstance().getPlayerByType(p).getField().getCharacterCardByID(characterId);
//        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
//        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
//        return (currPhase.equals(Phase.MAIN)) && card != null && !card.hasAttacked && currPlayer == p;
//    }
}
