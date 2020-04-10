package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Request.Render;
import com.avatarduel.guicontroller.Request.RenderRequest;
import com.avatarduel.guicontroller.Server.subscriber.Subscriber;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.Element;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PlayerStatusController implements Subscriber {
    private PlayerType playerType;
    @FXML Label player_name;
    @FXML ImageView player_image;
    @FXML Label player_hp;
    @FXML Label air_power;
    @FXML Label water_power;
    @FXML Label earth_power;
    @FXML Label fire_power;

    @FXML
    public void initialize() {
        setPlayerHealth(80);
        water_power.setText("0");
        fire_power.setText("0");
        earth_power.setText("0");
        air_power.setText("0");
    }

    @Subscribe
    public void update(RenderRequest request) {
        this.render();
    }

    public void setPlayerHealth(int health) {
        player_hp.setText("HP: " + Integer.toString(health));
    }

    public void setPlayerType(PlayerType player) {
        this.playerType = player;
    }

    public void render() {
        setPlayerHealth(Game.getInstance().getPlayerByType(playerType).getHealthPoint());
        water_power.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getCurrent_water()));
        fire_power.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getCurrent_fire()));
        air_power.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getCurrent_air()));
        earth_power.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getCurrent_earth()));
    }
}
