package com.avatarduel.event;

import com.avatarduel.exception.ExceptionCause.FullBoardCause;
import com.avatarduel.exception.ExceptionCause.InvalidPhaseCause;
import com.avatarduel.exception.ExceptionCause.NotEnoughPowerCause;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.exception.InvalidPhaseException;
import com.avatarduel.exception.NotEnoughPowerException;
import com.avatarduel.exception.NotEnoughSpaceException;
import com.avatarduel.factory.CardInFieldFactory;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

/**
 * SummonEvent is a event for summoning character from player hands.
 * Event will only executed if there are enough space in the field, else it will throw exception.
 *
 * IMPORTANT NOTE:
 * This event will communicate with game singleton instantly, so there are no need to validate
 * In case where event is not possible to do, we throw exception so that the GUI Board can give the
 * error message to the player playing the games
 * @author G10-K03-CardGameOOP
 */

public class SummonEvent implements IEvent {
    private PlayerType playerType;
    private int idCard;
    private CharacterState position;
    private int index;
    private CardInFieldFactory factory;
    public SummonEvent(int idCard, PlayerType playerType, CharacterState position, int index) {
        this.idCard = idCard;
        this.playerType = playerType;
        this.position = position;
        this.index = index;
        this.factory = new CardInFieldFactory();
    }
    public SummonEvent(int idCard, PlayerType playerType) throws InvalidOperationException {
        this(idCard,playerType,CharacterState.ATTACK,Game.getInstance().getPlayerByType(playerType).getField().getEmptyCharacterIndex());
    }
    @Override
    public void execute() throws InvalidOperationException {
        CharacterCard charCard = (CharacterCard) Game.getInstance().getPlayerByType(playerType).getHand()
                .stream()
                .filter(card -> card.getId() == idCard && card.getType().equals(CardType.CHARACTER))
                .findFirst()
                .orElse(null);
        int currTurn = Game.getInstance().getCurrentTurn();
        Player p = Game.getInstance().getPlayerByType(playerType);
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        int currentFieldSize = Game.getInstance().getPlayerByType(playerType).getField().getCharCardList().size();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();

        if (currPhase != Phase.MAIN){
            throw new InvalidPhaseException(new InvalidPhaseCause(charCard.getType()));
        }

        if (currentFieldSize >= Game.getInstance().getPlayerByType(playerType).getField().getFieldSize()){
            throw new NotEnoughSpaceException(new FullBoardCause(charCard.getType()));
        }

        if (charCard.getPower() > Game.getInstance().getPlayerByType(playerType).getPower().getCurrent(charCard.getElement())){
            throw new NotEnoughPowerException(new NotEnoughPowerCause(charCard.getElement()));
        }

        p.getHand().remove(charCard);
        p.getField().addCharacterCard((CharacterCardInField) factory.createCardInField(charCard, currTurn, index,position));
        p.getPower().reduce(charCard.getElement(), charCard.getPower());
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
