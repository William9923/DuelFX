package com.avatarduel.guicontroller.RenderRequest;

import com.avatarduel.model.type.PlayerType;

public abstract class RenderRequest {
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
