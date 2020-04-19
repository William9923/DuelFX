package com.avatarduel.guicontroller.Request.SpecificRequest;

import com.avatarduel.model.type.PlayerType;

/**
 * {@inheritDoc}
 * request for deck to draw and render
 * @author G10-K03-CardGameOOP
 */
public class DeckDrawAndRenderRequest extends SpecificPlayerRequest {
    public DeckDrawAndRenderRequest(PlayerType type) {
        super(type);
    }
}
