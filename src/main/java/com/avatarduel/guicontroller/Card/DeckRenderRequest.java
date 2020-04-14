package com.avatarduel.guicontroller.Card;

import com.avatarduel.guicontroller.Request.RenderRequest;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.PlayerType;

public class DeckRenderRequest extends RenderRequest {
    public DeckRenderRequest(PlayerType playerType) {
        super(playerType);
    }
}
