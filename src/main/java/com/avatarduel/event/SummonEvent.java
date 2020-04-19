package com.avatarduel.event;

import com.avatarduel.exception.*;
import com.avatarduel.exception.ExceptionCause.FullBoardCause;
import com.avatarduel.exception.ExceptionCause.InvalidPhaseCause;
import com.avatarduel.exception.ExceptionCause.NotEnoughPowerCause;
import com.avatarduel.factory.CardInFieldFactory;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;


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
            throw new InvalidSummonException(new InvalidPhaseCause(Phase.MAIN));
        }

        if (currentFieldSize >= Game.getInstance().getPlayerByType(playerType).getField().getFieldSize()){
            throw new InvalidSummonException(new FullBoardCause(charCard.getType()));
        }

        if (charCard.getPower() > Game.getInstance().getPlayerByType(playerType).getPower().getCurrent(charCard.getElement())){
            throw new InvalidSummonException(new NotEnoughPowerCause(charCard.getElement()));
        }

        p.getHand().remove(charCard);
        p.getField().addCharacterCard((CharacterCardInField) factory.createCardInField(charCard, currTurn, index,position));
        p.getPower().reduce(charCard.getElement(), charCard.getPower());
    }

    @Override
    public boolean validate(){
        CharacterCard charCard = (CharacterCard) Game.getInstance().getPlayerByType(playerType).getHand().stream()
                .filter(card -> card.getId() == idCard)
                .findFirst()
                .orElse(null);
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        int currentFieldSize = Game.getInstance().getPlayerByType(playerType).getField().getCharCardList().size();

        return ((currPhase == Phase.MAIN)
                && (currPlayer == playerType)
                && (charCard != null)
                && (CardType.CHARACTER == charCard.getType())
                && (currentFieldSize < Game.getInstance().getPlayerByType(playerType).getField().getFieldSize())
                && (charCard.getPower() <= Game.getInstance().getPlayerByType(playerType).getPower().getCurrent(charCard.getElement())));
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
