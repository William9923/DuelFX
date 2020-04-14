package com.avatarduel.guicontroller.MainMenu;

import com.avatarduel.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainMenuController {
    private Stage stage;

    @FXML private Label start;
    @FXML private Label how_to_play;
    @FXML private Label cards;

    @FXML
    public void initialize() {
    }

    public void setStage(Stage stage) throws IOException {
        this.stage = stage;
        this.setStartOnScene(getSceneFrom("GUI/Board/Board.fxml"));
        this.setHowToPlayOnScene(getSceneFrom("GUI/Tutorial/HowToPlay.fxml"));
        this.setCardsOnScene(getSceneFrom("GUI/Library/card_library.fxml"));
        Game.getInstance().getEventBus().register(this);
    }

    public void setStartOnScene(Scene scene) {
        start.onMouseClickedProperty().setValue(e -> {
            stage.setScene(scene);
            stage.setFullScreen(true);
        });
    }

    public void setHowToPlayOnScene(Scene scene) {
        how_to_play.onMouseClickedProperty().setValue(e -> {
            stage.setScene(scene);
        });
    }

    public void setCardsOnScene(Scene scene) {
        cards.onMouseClickedProperty().setValue(e -> {
            stage.setScene(scene);
        });
    }

    public Scene getSceneFrom(String filePath) throws IOException {
        File guiFile = new File("src/main/resources/com/avatarduel/" + filePath);
        FXMLLoader loader = new FXMLLoader(guiFile.toURI().toURL());
        Parent gui = loader.load();
        return new Scene(gui);
    }
}
