package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Server.subscriber.Subscriber;
import com.avatarduel.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameStatusController implements Subscriber {
    @FXML private Label game_turn;
    @FXML private Label current_player;
    @FXML private Label game_phase;

    @FXML
    public void initialize() {
        game_turn.setText(Integer.toString(Game.getInstance().getCurrentTurn()));
        current_player.setText(Game.getInstance().getCurrentPlayer().toString());
        game_phase.setText(Game.getInstance().getCurrentPhase().toString());
    }

    public void render() {
        game_turn.setText(Integer.toString(Game.getInstance().getCurrentTurn()));
        current_player.setText(Game.getInstance().getCurrentPlayer().toString());
        game_phase.setText(Game.getInstance().getCurrentPhase().getPhase().toString());
    }
}
