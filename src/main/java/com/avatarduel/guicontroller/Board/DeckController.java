package com.avatarduel.guicontroller.Board;

import com.avatarduel.event.DrawEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.guicontroller.Request.SpecificRequest.DeckDrawAndRenderRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.DeckRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 * used for rendering each player deck, render the remaining card from each player deck
 */
public class DeckController  {
    private PlayerType playerType;
    @FXML Label deck_size;
    @FXML Rectangle shape;

    /**
     * register the object to eventbus
     */
    @FXML
    public void initialize() {
        Game.getInstance().getEventBus().register(this);
    }

    /**
     * @Subscribe method for drawing card, and rendering the deck
     */
    @Subscribe
    public void drawAndRender(DeckDrawAndRenderRequest request) {
        if(playerType == request.getPlayerType()) {
            IEvent event = new DrawEvent(playerType);
            Game.getInstance().getEventBus().post(event);
            this.render();
        }
    }

    /**
     * @Subscribe method for drawing card, and rendering the deck
     */
    @Subscribe
    public void update(DeckRenderRequest renderRequest) {
        if (renderRequest.getPlayerType() == playerType){
            this.render();
        }
    }

    /**
     * updating deck's remaining card
     */
    public void render() {
        deck_size.setText("Remaining Card : " + Integer.toString(Game.getInstance().getPlayerByType(playerType).getDeck().size()));
    }

    /**
     * method for setting playerType and render the deck
     * @param playerType the player type
     */
    public void setPlayerTypeAndRender(PlayerType playerType) {
        this.playerType = playerType;
        this.render();
    }
}
