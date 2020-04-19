package com.avatarduel.guicontroller.Request.SpecificRequest;

import com.avatarduel.model.type.PlayerType;

/**
 * {@inheritDoc}
 * request to render the player status
 * @author G10-K03-CardGameOOP
 */
public class PlayerStatusRenderRequest extends SpecificPlayerRequest {
    public PlayerStatusRenderRequest(PlayerType playerType) {
        super(playerType);
    }
}
