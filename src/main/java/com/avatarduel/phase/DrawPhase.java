package com.avatarduel.phase;

import com.avatarduel.event.DrawEvent;
import com.avatarduel.model.type.Phase;

public class DrawPhase implements IPhase{

    private Phase phase;

    public DrawPhase() {
        phase = Phase.DRAW;
    }

//    public void drawCardAndGoToNextPhase() {
//        DrawEvent drawEvent = new DrawEvent();
//        drawEvent.execute();
//    }
    @Override
    public Phase getPhase() {
        return phase;
    }

    @Override
    public IPhase next() {
        return new MainPhase();
    }
}
