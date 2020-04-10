package com.avatarduel.guicontroller.Request;

import com.avatarduel.model.type.PlayerType;

public class RenderRequest {
    protected PlayerType playerType;

    public RenderRequest(PlayerType playerType) {
        this.playerType = playerType;
    }
    public RenderRequest() {
        this(PlayerType.A);
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
