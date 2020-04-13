package com.avatarduel.guicontroller.Request;

import com.avatarduel.model.Game;

public class CheckWinRequest extends RenderRequest {
    public CheckWinRequest() {
        super(Game.getInstance().getCurrentPlayer());
    }
}
