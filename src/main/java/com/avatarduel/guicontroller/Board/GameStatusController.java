package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Request.GlobalRequest.GameStatusRenderRequest;
import com.avatarduel.model.Game;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameStatusController {
    @FXML private Label game_turn;
    @FXML private Label current_player;
    @FXML private Label game_phase;

    @FXML
    public void initialize() {
        Game.getInstance().getEventBus().register(this);
        game_turn.setText(Integer.toString(Game.getInstance().getCurrentTurn()));
        current_player.setText(Game.getInstance().getCurrentPlayer().toString());
        game_phase.setText(Game.getInstance().getCurrentPhase().getPhase().toString());
    }

    @Subscribe
    public void update(GameStatusRenderRequest request) {
        this.render();
    }
    public void render() {
        game_turn.setText(Integer.toString(Game.getInstance().getCurrentTurn()));
        current_player.setText(Game.getInstance().getCurrentPlayer().toString());
        game_phase.setText(Game.getInstance().getCurrentPhase().getPhase().toString());
    }

}
