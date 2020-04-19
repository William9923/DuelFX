package com.avatarduel.guicontroller.Request.SpecificRequest;

import com.avatarduel.model.type.PlayerType;

/**
 * {@inheritDoc}
 * request to render the hand
 * @author G10-K03-CardGameOOP
 */
public class HandRenderRequest extends SpecificPlayerRequest {
    public HandRenderRequest(PlayerType type) {
        super(type);
    }
}
