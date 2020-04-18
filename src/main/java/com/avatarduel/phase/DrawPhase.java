package com.avatarduel.phase;

import com.avatarduel.event.DrawEvent;
import com.avatarduel.model.type.Phase;

public class DrawPhase implements IPhase{

    private Phase phase;

    //Constructor for DrawPhase
    public DrawPhase() {
        phase = Phase.DRAW;
    }

//    public void drawCardAndGoToNextPhase() {
//        DrawEvent drawEvent = new DrawEvent();
//        drawEvent.execute();
//    }

    //Method to return current Phase
    @Override
    public Phase getPhase() {
        return phase;
    }

    //Method to get to the next phase
    @Override
    public IPhase next() {
        return new MainPhase();
    }
}
