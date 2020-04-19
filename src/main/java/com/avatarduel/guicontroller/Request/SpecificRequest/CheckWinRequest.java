package com.avatarduel.guicontroller.Request.SpecificRequest;

import com.avatarduel.model.Game;

/**
 * {@inheritDoc}
 * request to check if the game has ended or not
 * @author G10-K03-CardGameOOP
 */
public class CheckWinRequest extends SpecificPlayerRequest {
    public CheckWinRequest() {
        super(Game.getInstance().getCurrentPlayer());
    }
}
