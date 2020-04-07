package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.factory.CardInFieldFactory;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCard;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class ActivateSkillEvent implements IEvent { // has not implemented yet

    private PlayerType playerType;
    private int index;
    private CardInFieldFactory factory;
    private int currTurn;
    private int idCard;
    private int idTarget;

    public ActivateSkillEvent(int idCard, int idTarget, PlayerType playerType, int index) {
        this.playerType = playerType;
        this.index = index;
        this.factory = new CardInFieldFactory();
        this.idCard = idCard;
        this.idTarget = idTarget;
        this.currTurn = Game.getInstance().getCurrentTurn();
    }
    @Override
    public void execute() {
        SkillCard skillCard = (SkillCard) Game.getInstance().getPlayerByType(playerType).getHand()
                .stream()
                .filter(card1 -> card1.getId() == idCard && (card1.getType().equals(CardType.SKILL_AURA) || (card1.getType().equals(CardType.SKILL_POWER_UP))))
                .findFirst()
                .orElse(null);
        Player p = Game.getInstance().getPlayerByType(playerType);
        try {
            p.playSkillCardByID(idCard, idTarget, index, currTurn);
            p.getPower().reduce(skillCard.getElement(), skillCard.getPower()); // kalo error, dia ga kekurang powernya jdny
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validate(){
        SkillCard skillCard = (SkillCard) Game.getInstance().getPlayerByType(playerType).getHand()
                .stream()
                .filter(card1 -> card1.getId() == idCard && (card1.getType().equals(CardType.SKILL_AURA) || (card1.getType().equals(CardType.SKILL_POWER_UP))))
                .findFirst()
                .orElse(null);
        CharacterCardInField characterCardInField = (CharacterCardInField) Game.getInstance().getPlayerByType(playerType).getField().getCharCardList()
                .stream()
                .filter(c -> c.getCard().getId() == idTarget && c.getCard().getType().equals(CardType.CHARACTER))
                .findFirst()
                .orElse(null);
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        int currentFieldSize = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList().size();

        return (((currPhase == Phase.MAIN1) || (currPhase == Phase.MAIN2))
                && (currPlayer == playerType)
                && (characterCardInField != null)
                && (skillCard != null)
                && (CardType.CHARACTER == characterCardInField.getCard().getType())
                && (currentFieldSize < Game.getInstance().getPlayerByType(playerType).getField().getFieldSize())
                && (skillCard.getPower() <= Game.getInstance().getPlayerByType(playerType).getPower().getCurrent(skillCard.getElement())));
    }
}
