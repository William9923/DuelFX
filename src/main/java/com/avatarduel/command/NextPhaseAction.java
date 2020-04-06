package com.avatarduel.command;

import com.avatarduel.model.Game;
import com.avatarduel.model.type.Phase;
import com.avatarduel.phase.MainPhase2;

public class NextPhaseAction implements IAction {

    public NextPhaseAction() {
        // untuk sementara, validasi next phase cukup gini saja
    }

    @Override
    public void execute() {
        Game.getInstance().nextPhase(); // pindah phase
    }

    @Override
    public boolean validate() {
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        return (currPhase == Phase.MAIN1 || currPhase == Phase.BATTLE); // cuman bisa pas MainPhase 1 atau battle, kalo MainPhase2 pake tombol
    }
}
