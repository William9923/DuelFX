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
    public void execute() throws InvalidOperationException{
        Game.getInstance().getPlayerByType(playerType).removeSkillCardByID(idTarget);
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
        return (((currPhase == Phase.MAIN))
                && (currPlayer == playerType)
                && (card != null));

    }
}
