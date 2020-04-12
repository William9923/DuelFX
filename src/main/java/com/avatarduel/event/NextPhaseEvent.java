package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.Phase;

public class NextPhaseEvent implements IEvent {

    public NextPhaseEvent() {
        // untuk sementara, validasi next phase cukup gini saja
    }

    @Override
    public void execute() throws InvalidOperationException {
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        if (currPhase.equals(Phase.BATTLE)){
            IEvent event = new EndTurnEvent();
            event.execute();
        } else {
            Game.getInstance().nextPhase();
        }
    }

    @Override
    public boolean validate() {
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        return (currPhase == Phase.MAIN || currPhase == Phase.BATTLE); // cuman bisa pas MainPhase 1 atau battle, kalo MainPhase2 pake tombol
    }
}
