package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class RemoveSkillCardEvent implements IEvent { // has not implemented yet

    private int idTarget;
    private PlayerType playerType;
    public RemoveSkillCardEvent(int idTarget, PlayerType player) {
        this.idTarget = idTarget;
        this.playerType = player;
    }

    @Override
    public void execute() {
        try {
            Game.getInstance().getPlayerByType(playerType).removeSkillCardByID(idTarget);
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validate() {
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        SkillCardInField card = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList()
                .stream()
                .filter(c -> c.getCard().getId() == idTarget)
                .findFirst()
                .orElse(null);
        return (((currPhase == Phase.MAIN1) || (currPhase == Phase.MAIN2))
                && (currPlayer == playerType)
                && (card != null));

    }
}
