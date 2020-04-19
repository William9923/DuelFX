package com.avatarduel.guicontroller.Request.SpecificRequest;

import com.avatarduel.guicontroller.Request.Request;
import com.avatarduel.model.type.PlayerType;

public abstract class SpecificPlayerRequest implements Request {
    protected PlayerType playerType;

    public SpecificPlayerRequest(PlayerType playerType) {
        this.playerType = playerType;
    }
    public SpecificPlayerRequest() {
        this(PlayerType.A);
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
