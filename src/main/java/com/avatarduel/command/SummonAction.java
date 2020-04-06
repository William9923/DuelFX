package com.avatarduel.command;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class SummonAction implements IAction{
    private PlayerType playerType;
    private int idCard;
    private CharacterState position;

    public SummonAction(int idCard, PlayerType playerType, CharacterState position) {
        this.idCard = idCard;
        this.playerType = playerType;
        this.position = position;
    }
    @Override
    public void execute() {
        CharacterCard charCard = (CharacterCard) Game.getInstance().getPlayerByType(playerType).getHand()
                .stream()
                .filter(card -> card.getId() == idCard && card.getType().equals(CardType.CHARACTER))
                .findFirst()
                .orElse(null);
        int currTurn = Game.getInstance().getCurrentTurn();
        CharacterCardInField newInField = new CharacterCardInField(charCard,position,currTurn );
        try {
            Game.getInstance().getPlayerByType(playerType).getField().addCharacterCard(newInField);
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        }
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

        return (((currPhase == Phase.MAIN1) || (currPhase == Phase.MAIN2))
                && (currPlayer == playerType)
                && (charCard != null)
                && (CardType.CHARACTER == charCard.getType())
                && (currentFieldSize < Game.getInstance().getPlayerByType(playerType).getField().getFieldSize()));
    }
}
