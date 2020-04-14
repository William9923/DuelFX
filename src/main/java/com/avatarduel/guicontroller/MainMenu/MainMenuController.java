package com.avatarduel.guicontroller.MainMenu;

import com.avatarduel.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Popup;
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
        this.setHowToPlayPopUpOnParent(getParentFrom("GUI/MainMenu/HowToPlay/Page3.fxml"));
        this.setCardsOnScene(getSceneFrom("GUI/MainMenu/ShowCards/ShowCards.fxml"));
        Game.getInstance().getEventBus().register(this);
    }

    public void setStartOnScene(Scene scene) {
        start.onMouseClickedProperty().setValue(e -> {
            stage.setScene(scene);
            stage.setFullScreen(true);
        });
    }

    public void setHowToPlayPopUpOnParent(Parent parent) {
        Popup howToPlay = new Popup();
        howToPlay.getContent().add(parent);
        how_to_play.onMouseClickedProperty().setValue(e -> {
            howToPlay.show(stage);
        });
    }

    public void setCardsOnScene(Scene scene) {
        cards.onMouseClickedProperty().setValue(e -> {
            stage.setScene(scene);
        });
    }

    public Scene getSceneFrom(String filePath) throws IOException {
        Parent gui = getParentFrom(filePath);
        return new Scene(gui);
    }

    public Parent getParentFrom(String filePath) throws IOException {
        File guiFile = new File("src/main/resources/com/avatarduel/" + filePath);
        FXMLLoader loader = new FXMLLoader(guiFile.toURI().toURL());
        return loader.load();
    }
}
