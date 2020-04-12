package com.avatarduel.event;

import com.avatarduel.exception.InvalidActionException;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.factory.CardInFieldFactory;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

import java.util.List;

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
            throw new InvalidOperationException("Summon", "Unable to perform summon in this phase");
        }

        if (currPlayer != playerType) {
            throw new InvalidOperationException("Summon", "Unable to summon monster from other hands");
        }

        if (charCard == null) {
            throw new InvalidOperationException("Summon", "Character Card is Invalid");
        }

        if (charCard.getType() != CardType.CHARACTER){
            throw new InvalidOperationException("Summon", "Card is not a character card");
        }

        if (currentFieldSize >= Game.getInstance().getPlayerByType(playerType).getField().getFieldSize()){
            throw new InvalidOperationException("Summon", "There are not enough space in field for this character");
        }

        if (charCard.getPower() > Game.getInstance().getPlayerByType(playerType).getPower().getCurrent(charCard.getElement())){
            throw new InvalidOperationException("Summon", "Not enough power!");
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
