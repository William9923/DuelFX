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
        this.setHowToPlayPopUp();
        this.setCardsPopUp();
        Game.getInstance().getEventBus().register(this);
    }

    public void setStartOnScene(Scene scene) {
        start.onMouseClickedProperty().setValue(e -> {
            stage.setScene(scene);
            stage.setFullScreen(true);
        });
    }

    public void setHowToPlayPopUp() {
        HowToPlayController howToPlay = new HowToPlayController();
        howToPlay.setStage(stage);
        how_to_play.onMouseClickedProperty().setValue(e -> {
            howToPlay.goToPage1();
        });
    }

    public void setCardsPopUp() throws IOException {
        FXMLLoader fxmlLoader = getFxmlLoader("GUI/MainMenu/ShowCards/ShowCards.fxml");
        fxmlLoader.load(); //harus diginiin sebelum getController dipanggil
        CardLibraryController cardLibraryController = fxmlLoader.getController();
        cardLibraryController.setStage(stage);
        cards.onMouseClickedProperty().setValue(e -> {
            cardLibraryController.start();
        });
    }

    public Scene getSceneFrom(String filePath) throws IOException {
        Parent gui = getParentFrom(filePath);
        return new Scene(gui);
    }

    public Parent getParentFrom(String filePath) throws IOException {
        FXMLLoader loader = getFxmlLoader(filePath);
        return loader.load();
    }

    public FXMLLoader getFxmlLoader(String filePath) throws IOException {
        File guiFile = new File("src/main/resources/com/avatarduel/" + filePath);
        return new FXMLLoader(guiFile.toURI().toURL());
    }
}
