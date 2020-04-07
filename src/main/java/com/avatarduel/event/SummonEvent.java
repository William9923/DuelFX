package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.factory.CardInFieldFactory;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCard;
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
    @Override
    public void execute() {
        CharacterCard charCard = (CharacterCard) Game.getInstance().getPlayerByType(playerType).getHand()
                .stream()
                .filter(card -> card.getId() == idCard && card.getType().equals(CardType.CHARACTER))
                .findFirst()
                .orElse(null);
        int currTurn = Game.getInstance().getCurrentTurn();
        Player p = Game.getInstance().getPlayerByType(playerType);
//        CharacterCardInField newInField = (CharacterCardInField) factory.createCardInField(charCard, currTurn, index,position);

        try {
            p.playCharacterCardByID(idCard, position, currTurn, index); // cek dl error ato ga
            p.getPower().reduce(charCard.getElement(), charCard.getPower()); // kalo error, dia ga kekurang powernya jdny
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
                && (currentFieldSize < Game.getInstance().getPlayerByType(playerType).getField().getFieldSize())
                && (charCard.getPower() <= Game.getInstance().getPlayerByType(playerType).getPower().getCurrent(charCard.getElement())));
    }
}
