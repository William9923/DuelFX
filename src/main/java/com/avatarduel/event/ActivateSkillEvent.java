package com.avatarduel.event;

import com.avatarduel.exception.*;
import com.avatarduel.exception.ExceptionCause.*;
import com.avatarduel.factory.CardInFieldFactory;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCard;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

/**
 * ActivateSkillEvent is a event for activating aura or power up skill cards.
 *
 * This event connect the card to the character specify by player, and will be destroyed in 2 (two) cases :
 * 1. The player who played this card removed it from field
 * 2. The character the card is attached to is removed from field (either destroyed or lose in battle)
 * IMPORTANT NOTE:
 * This event will communicate with game singleton instantly, so there are no need to validate
 * In case where event is not possible to do, we throw exception so that the GUI Board can give the
 * error message to the player playing the games
 * @author G10-K03-CardGameOOP
 */

public class ActivateSkillEvent implements IEvent {

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
    public ActivateSkillEvent(int idCard, int idTarget, PlayerType playerType) {
        this(idCard, idTarget, playerType, Game.getInstance().getPlayerByType(playerType).getField().getEmptySkillCardIndex());
    }
    @Override
    public void execute() throws InvalidOperationException {
        SkillCard skillCard = (SkillCard) Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHand()
                .stream()
                .filter(card1 -> card1.getId() == idCard && (card1.getType().equals(CardType.SKILL_AURA) || (card1.getType().equals(CardType.SKILL_POWER_UP))))
                .findFirst()
                .orElse(null);

        PlayerType targetedPlayer = Game.getInstance().getCurrentPlayer();
        CharacterCardInField inField = Game.getInstance().getPlayerByType(targetedPlayer).getField()
                    .getCharCardList()
                    .stream()
                    .filter(c -> c.getCard().getId() == idTarget)
                    .findFirst()
                    .orElse(null);
        if (inField == null) {
            targetedPlayer = Game.getInstance().getCurrentOpponent();
            inField = Game.getInstance().getPlayerByType(targetedPlayer).getField()
                    .getCharCardList()
                    .stream()
                    .filter(c -> c.getCard().getId() == idTarget)
                    .findFirst()
                    .orElse(null);
        }

        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        int currentFieldSize = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList().size();


        if (currPhase != Phase.MAIN) {
            throw new InvalidSkillActivationException(new InvalidPhaseCause(Phase.MAIN));
        }

        if (inField == null) {
            throw new InvalidSkillActivationException(new NoCharacterCardInFieldCause(skillCard.getType()));
        }

        if (currentFieldSize >= Game.getInstance().getPlayerByType(playerType).getField().getFieldSize()) {
            throw new InvalidSkillActivationException(new FullBoardCause(skillCard.getType()));
        }

        if (skillCard.getPower() > Game.getInstance().getPlayerByType(playerType).getPower().getCurrent(skillCard.getElement())) {
            throw new InvalidSkillActivationException(new NotEnoughPowerCause(skillCard.getElement()));
        }

        if (skillCard.getType() == CardType.SKILL_POWER_UP) {
            System.out.println("Activate Power Up");
        }

        Player p = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer());
        p.getHand().remove(skillCard);
        p.getField().addSkillCard(skillCard, index, Game.getInstance().getCurrentTurn());
        inField.pair(skillCard);
        p.getPower().reduce(skillCard.getElement(), skillCard.getPower()); // kalo error, dia ga kekurang powernya jdny

    }

}
