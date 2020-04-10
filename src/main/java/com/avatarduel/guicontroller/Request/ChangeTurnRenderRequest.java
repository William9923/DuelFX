package com.avatarduel.guicontroller.Request;

import com.avatarduel.model.Game;

public class ChangeTurnRenderRequest extends RenderRequest {
    public ChangeTurnRenderRequest() {
        super(Game.getInstance().getCurrentPlayer());
    }
}
