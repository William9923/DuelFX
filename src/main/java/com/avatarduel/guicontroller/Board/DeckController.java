package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Server.subscriber.Subscriber;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DeckController implements Subscriber {
    private PlayerType playerType;
    @FXML Label deck_size;

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
