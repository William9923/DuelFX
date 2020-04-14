package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.avatarduel.factory.CardFactory;
import com.avatarduel.guicontroller.Board.BoardController;
import com.avatarduel.guicontroller.Card.DisplayCardController;
import com.avatarduel.guicontroller.MainMenu.MainMenuController;
import com.avatarduel.model.card.SkillAuraCard;
import com.avatarduel.util.CSVReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.media.Media;

public class AvatarDuel extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    // Load Main Menu
    FXMLLoader mainmenuLoader = getMainMenu();
    Parent mainmenuGUI = mainmenuLoader.load();
    Scene mainmenu = new Scene(mainmenuGUI);
    MainMenuController mainMenuController = mainmenuLoader.getController();
    mainMenuController.setStage(stage);

    stage.setTitle("Avatar Duel OOP");
    stage.setScene(mainmenu);
    stage.setFullScreen(true);
    stage.show();
  }

  private FXMLLoader getMainMenu() {
    return new FXMLLoader(getClass().getResource("GUI/MainMenu/MainMenu.fxml"));
  }

  private void playThemeMusic() throws URISyntaxException {
    Media media = new Media(getClass().getResource("music/main_menu_song.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setVolume(0.2);
    mediaPlayer.play();
  }

  public static void main(String[] args) {
    launch();
  }
}