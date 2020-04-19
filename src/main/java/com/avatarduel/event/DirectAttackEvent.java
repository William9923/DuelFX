package com.avatarduel.event;

import com.avatarduel.exception.ExceptionCause.AttackOnTheCreatedTurnCause;
import com.avatarduel.exception.ExceptionCause.MultipleAttackOnTheSameTurnCause;
import com.avatarduel.exception.InvalidAttackException;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.player_component.Field;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

/**
 * DirectAttackEvent is a event for attacking other opponent directly.
 *
 * Only valid when there are no character in opponent field
 *
 * IMPORTANT NOTE:
 * This event will communicate with game singleton instantly, so there are no need to validate
 * In case where event is not possible to do, we throw exception so that the GUI Board can give the
 * error message to the player playing the games
 *
 * @author G10-K03-CardGameOOP
 */

public class DirectAttackEvent implements IEvent {

    private PlayerType player;
    private int attackCharacterId;

    public DirectAttackEvent(int idAttack, PlayerType attacker) {
        this.attackCharacterId = idAttack;
        this.player = attacker;
    }

    /**
     * execute method to run the event invoked by user action
     */
    @Override
    public void execute()  throws InvalidOperationException {
        Field f1 = Game.getInstance()
                .getPlayerByType(player)
                .getField();
        int currentTurn = Game.getInstance().getCurrentTurn();
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        CharacterCardInField attackChar = Game.getInstance()
                .getPlayerByType(player)
                .getField()
                .getCharacterCardByID(attackCharacterId);

        if (f1.getCharacterCardByID(attackCharacterId).getCreatedAtTurn() == currentTurn){
            throw new InvalidAttackException(new AttackOnTheCreatedTurnCause());
        }

        if (f1.getCharacterCardByID(attackCharacterId).hasAttacked) {
            throw new InvalidAttackException(new MultipleAttackOnTheSameTurnCause());
        }

        attackChar.hasAttacked = true; // change state monster yang uda nyerang
        Player p2 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()); // ambil reference player 2
        p2.setHealthPoint(p2.getHealthPoint() - attackChar.getTotalAttack()); // kurangin health point player lawan
    }
}
