package com.avatarduel.guicontroller.RenderRequest;

import com.avatarduel.model.Game;

public class ChangeTurnRenderRequest extends RenderRequest {
    public ChangeTurnRenderRequest() {
        super(Game.getInstance().getCurrentPlayer());
    }
}
