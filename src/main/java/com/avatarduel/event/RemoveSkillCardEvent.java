package com.avatarduel.event;

import com.avatarduel.exception.ExceptionCause.InvalidPhaseCause;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.exception.InvalidRemoveSkillcardException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * RemoveSkillCardEvent is a event for removing skill card that are on the field.
 *
 * IMPORTANT NOTE:
 * Only able to remove the card that are already equipped / placed in the field
 * This event will communicate with game singleton instantly, so there are no need to validate
 * In case where event is not possible to do, we throw exception so that the GUI Board can give the
 * error message to the player playing the games
 * @author G10-K03-CardGameOOP
 */

public class RemoveSkillCardEvent implements IEvent { // has not implemented yet

    private int idTarget;
    private PlayerType playerType;
    public RemoveSkillCardEvent(int idTarget, PlayerType player) {
        this.idTarget = idTarget;
        this.playerType = player;
    }

    /**
     * execute method to run the event invoked by user action
     */
    @Override
    public void execute() throws InvalidOperationException{
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        SkillCardInField card = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList()
                .stream()
                .filter(c -> c.getCard().getId() == idTarget)
                .findFirst()
                .orElse(null);

        List<CharacterCardInField> list1 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getCharCardList();
        List<CharacterCardInField> list2 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList();

        CharacterCardInField pairedCharacter = list1.stream()
                .filter(c -> c.getConnectedCard().contains(card.getCard()))
                .findFirst()
                .orElse(null);

        if (pairedCharacter == null) {
            pairedCharacter = list2.stream()
                    .filter(c -> c.getConnectedCard().contains(card.getCard()))
                    .findFirst()
                    .orElse(null);
        }

        if (currPhase != Phase.MAIN){
            throw new InvalidRemoveSkillcardException(new InvalidPhaseCause(Phase.MAIN));
        }

        pairedCharacter.setConnectedCard(pairedCharacter.getConnectedCard().stream()
                .filter(c-> c.getId() !=card.getCard().getId())
                .collect(Collectors.toList()));

        List<SkillCardInField> cardInFields = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getSkillCardList();
        Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().setSkillCardList(cardInFields.stream()
                .filter(c -> c.getCard().getId() != idTarget)
                .collect(Collectors.toList()));
    }

}
