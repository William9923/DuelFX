package com.avatarduel.guicontroller.Request.SpecificRequest;

import com.avatarduel.model.Game;

public class CheckWinRequest extends SpecificPlayerRequest {
    public CheckWinRequest() {
        super(Game.getInstance().getCurrentPlayer());
    }
}
