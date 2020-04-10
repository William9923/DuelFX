package com.avatarduel.guicontroller.Request;

import com.avatarduel.model.Game;

public class HandRenderRequest extends RenderRequest{
    public HandRenderRequest() {
        super(Game.getInstance().getCurrentPlayer());
    }
}
