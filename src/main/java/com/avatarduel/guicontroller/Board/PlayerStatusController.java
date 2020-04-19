package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Request.SpecificRequest.PlayerStatusRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class PlayerStatusController  {
    private static int numOfPlayer = 0;
    private PlayerType playerType;
    @FXML Label player_name;
    @FXML ImageView player_image;
    @FXML Label player_hp;
    @FXML Label current_air;
    @FXML Label current_water;
    @FXML Label current_fire;
    @FXML Label current_earth;
    @FXML Label current_energy;

    @FXML Label total_air;
    @FXML Label total_water;
    @FXML Label total_fire;
    @FXML Label total_earth;
    @FXML Label total_energy;

    @FXML
    public void initialize() {
        Game.getInstance().getEventBus().register(this);
        numOfPlayer++;
        setPlayerHealth(80);
        current_earth.setText("0");
        current_fire.setText("0");
        current_water.setText("0");
        current_air.setText("0");
        current_energy.setText("0");
        total_air.setText("0");
        total_water.setText("0");
        total_fire.setText("0");
        total_earth.setText("0");
        total_energy.setText("0");
        if(numOfPlayer == 2) {
            player_name.setText("Jotaro Kujo");
            try {
                File imageFile = new File("src/main/resources/com/avatarduel/gui/board/character/jotaro_kujo.png");
                player_image.setImage(new Image("file:\\" + imageFile.getAbsolutePath()));
            }
            catch (Exception e) { }
        }
    }

    @Subscribe
    public void update(PlayerStatusRenderRequest request) {
        this.render();
    }

    public void setPlayerHealth(int health) {
        player_hp.setText( Integer.toString(health));
    }

    public void setPlayerType(PlayerType player) {
        this.playerType = player;
    }

    public void render() {
        setPlayerHealth(Math.max(0, Game.getInstance().getPlayerByType(playerType).getHealthPoint()));  // mastiin dia ga mungkin minus
        current_earth.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getCurrent_earth()));
        current_fire.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getCurrent_fire()));
        current_water.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getCurrent_water()));
        current_air.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getCurrent_air()));
        current_energy.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getCurrent_energy()));
        total_earth.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getTotal_earth()));
        total_fire.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getTotal_fire()));
        total_water.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getTotal_water()));
        total_air.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getTotal_air()));
        total_energy.setText(Integer.toString(Game.getInstance().getPlayerByType(playerType).getPower().getTotal_energy()));
    }
}
