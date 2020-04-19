package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Request.GlobalRequest.GameStatusRenderRequest;
import com.avatarduel.model.Game;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * show the game status, such as phase, turn number, and current player
 */
public class GameStatusController {
    @FXML private Label game_turn;
    @FXML private Label current_player;
    @FXML private Label game_phase;

    /**
     * initialize the componnets, and registering this to eventbus
     */
    @FXML
    public void initialize() {
        Game.getInstance().getEventBus().register(this);
        game_turn.setText(Integer.toString(Game.getInstance().getCurrentTurn()));
        current_player.setText(Game.getInstance().getCurrentPlayer().toString());
        game_phase.setText(Game.getInstance().getCurrentPhase().getPhase().toString());
    }

    /**
     * @Subscribe method for rendering the game status
     */
    @Subscribe
    public void update(GameStatusRenderRequest request) {
        this.render();
    }

    /**
     * render the game status, update the phase, turn, and current player
     */
    public void render() {
        game_turn.setText(Integer.toString(Game.getInstance().getCurrentTurn()));
        current_player.setText(Game.getInstance().getCurrentPlayer().toString());
        game_phase.setText(Game.getInstance().getCurrentPhase().getPhase().toString());
    }

}
