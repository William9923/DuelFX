package com.avatarduel.guicontroller.Request.SpecificRequest;

import com.avatarduel.guicontroller.Request.Request;
import com.avatarduel.model.type.PlayerType;

/**
 * The request is specific to a player. It means that
 * if the request's player type does not match the receiver player
 * type, the receiver will not do what the request asks
 * @author G10-K03-CardGameOOP
 */
public abstract class SpecificPlayerRequest implements Request {
    protected PlayerType playerType;

    /**
     * @param playerType specifies what player this request is asked to
     */
    public SpecificPlayerRequest(PlayerType playerType) {
        this.playerType = playerType;
    }

    /**
     * @return the player type
     */
    public PlayerType getPlayerType() {
        return playerType;
    }

    /**
     * set up this card request player type
     * @param playerType the player type
     */
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
