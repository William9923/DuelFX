package com.avatarduel.guicontroller.Board;

import com.avatarduel.event.DrawEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.guicontroller.RenderRequest.DeckRenderRequest;
import com.avatarduel.guicontroller.RenderRequest.*;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class DeckController  {
    private PlayerType playerType;
    @FXML Label deck_size;
    @FXML Rectangle shape;

    @FXML
    public void initialize() { }

    @Subscribe
    public void drawAndRender(DeckDrawAndRenderRequest request) {
        if(playerType == request.getPlayerType()) {
            Game.getInstance().getEventBus().post(new CheckWinRequest());
            IEvent event = new DrawEvent(playerType);
            Game.getInstance().getEventBus().post(event);
            Game.getInstance().getEventBus().post(new HandRenderRequest(Game.getInstance().getCurrentPlayer()));
            Game.getInstance().getEventBus().post(new DeckRenderRequest(playerType));
            Game.getInstance().getEventBus().post(new GameStatusRenderRequest());
        }
    }

    @Subscribe
    public void update(DeckRenderRequest renderRequest) {
        if (renderRequest.getPlayerType() == playerType){
            this.render();
        }
    }

    public void render() {
        deck_size.setText("Remaining Card : " + Integer.toString(Game.getInstance().getPlayerByType(playerType).getDeck().size()));
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
