package com.avatarduel.guicontroller.Board;

import com.avatarduel.event.DrawEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.guicontroller.Request.Render;
import com.avatarduel.guicontroller.Server.subscriber.Subscriber;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class DeckController implements Subscriber {
    private PlayerType playerType;
    @FXML Label deck_size;
    @FXML Rectangle shape;

    @FXML
    public void initialize() {
        shape.onMouseClickedProperty().setValue(e -> {
            this.draw();
        });
    }

    @FXML
    public void draw() {
        IEvent event = new DrawEvent();
        Game.getInstance().getEventBus().post(event);
    }

    @Subscribe
    public void update(Render request) {
        this.render();
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
