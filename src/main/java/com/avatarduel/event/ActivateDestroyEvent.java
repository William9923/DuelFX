package com.avatarduel.event;

import com.avatarduel.exception.*;
import com.avatarduel.exception.ExceptionCause.InvalidPhaseCause;
import com.avatarduel.exception.ExceptionCause.InvalidTargetCause;
import com.avatarduel.exception.ExceptionCause.NotEnoughPowerCause;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCard;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ActivateDestroyEvent is a event for activating destroy skill cards.
 * IMPORTANT NOTE:
 * This event will communicate with game singleton instantly, so there are no need to validate
 * In case where event is not possible to do, we throw exception so that the GUI Board can give the
 * error message to the player playing the games
 * @author G10-K03-CardGameOOP
 */

public class ActivateDestroyEvent implements IEvent {

    private int skillID;
    private int targetID;
    private PlayerType playerType; // enchanter

    public ActivateDestroyEvent(PlayerType playerType, int skillID, int charID) {
        this.playerType = playerType;
        this.skillID = skillID;
        this.targetID = charID;
    }

    /**
     * execute method to run the event invoked by user action
     */
    @Override
    public void execute() throws InvalidOperationException {
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        Player opponent = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent());
        Player player = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer());
        Card destroyCard = player.getHand().stream()
                .filter(card -> card.getId() == skillID)
                .findFirst()
                .orElse(null);

        assert destroyCard != null;

        if (!currPhase.equals(Phase.MAIN)){
            throw new InvalidSkillActivationException(new InvalidPhaseCause(Phase.MAIN));
        }

        if (opponent.getField().getCharacterCardByID(targetID) == null){
            throw new InvalidSkillActivationException(new InvalidTargetCause(destroyCard.getType()));
        }

        if (player.getPower().getCurrent(destroyCard.getElement()) < destroyCard.getPower()){
            throw new InvalidSkillActivationException(new NotEnoughPowerCause(destroyCard.getElement()));
        }
        // reduce power
        player.getPower().reduce(destroyCard.getElement(),destroyCard.getPower());
        CharacterCardInField cardInField = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList()
                .stream()
                .filter(c -> c.getCard().getId() == targetID)
                .findFirst()
                .orElse(null);

        List<SkillCard> pairedSkillCard = cardInField.getConnectedCard();
        for (SkillCard card: pairedSkillCard) {
            List<SkillCardInField> list1 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getSkillCardList();
            List<SkillCardInField> list2 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getSkillCardList();

            Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().setSkillCardList(list1.stream()
                    .filter(c -> c.getCard().getId() != card.getId())
                    .collect(Collectors.toList()));
            Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().setSkillCardList(list2.stream()
                    .filter(c -> c.getCard().getId() != card.getId())
                    .collect(Collectors.toList()));
        }

        // destroy card
        opponent.getField().removeCharacterCard(opponent.getField().getCharacterCardByID(targetID));
        // send destroy card to graveyard
        player.getHand().remove(destroyCard);
    }
}
