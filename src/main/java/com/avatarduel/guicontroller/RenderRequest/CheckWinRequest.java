package com.avatarduel.guicontroller.RenderRequest;

import com.avatarduel.model.Game;

public class CheckWinRequest extends RenderRequest {
    public CheckWinRequest() {
        super(Game.getInstance().getCurrentPlayer());
    }
}
