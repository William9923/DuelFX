package com.avatarduel.guicontroller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainMenuController {
    @FXML private Label start;
    @FXML private Label how_to_play;
    @FXML private Label cards;

    public void setStartOnScene(Stage stage,Scene scene) {
        start.onMouseClickedProperty().setValue(e -> {
            stage.setScene(scene);
        });
    }

    public void setHowToPlayOnScene(Stage stage, Scene scene) {
        how_to_play.onMouseClickedProperty().setValue(e -> {
            stage.setScene(scene);
        });
    }

    public void setCardsOnScene(Stage stage, Scene scene) {
        cards.onMouseClickedProperty().setValue(e -> {
            stage.setScene(scene);
        });
    }

}
