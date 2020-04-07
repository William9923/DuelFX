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
import javafx.stage.Stage;
import javafx.scene.media.Media;

public class AvatarDuel extends Application {

  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String CHARACTER_CSV_FILE_PATH ="card/data/character.csv";
  private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
//    Parent root = FXMLLoader.load(getClass().getResource("Board.fxml"));
    // Load Main Menu
    playThemeMusic();
    FXMLLoader mainmenuLoader = getMainMenu();
    Parent mainmenuGUI = mainmenuLoader.load();
    Scene mainmenu = new Scene(mainmenuGUI);
    MainMenuController mainMenuController = mainmenuLoader.getController();

    // load board
    FXMLLoader FXMLBoardGUI = new FXMLLoader(getClass().getResource("GUI/Board/Board.fxml"));
    Parent boardGUI = FXMLBoardGUI.load();
    Scene board = new Scene(boardGUI);
    BoardController boardController = FXMLBoardGUI.getController();

    // Load card library
    FXMLLoader libraryLoader = new FXMLLoader(getClass().getResource("GUI/Library/card_library.fxml"));
    Parent libraryGUI = libraryLoader.load();
    Scene library = new Scene(libraryGUI);


    stage.setTitle("Avatar Duel");
    stage.setScene(mainmenu);
    stage.setFullScreen(true);
    stage.show();

    mainMenuController.setStartOnScene(stage, board);
    mainMenuController.setCardsOnScene(stage, library);
  }

  private FXMLLoader getMainMenu() {
    return new FXMLLoader(getClass().getResource("GUI/MainMenu/MainMenu.fxml"));
  }

  private void playThemeMusic() throws URISyntaxException {
    Media media = new Media(getClass().getResource("music/main_menu_song.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
    mediaPlayer.setVolume(50);
  }

  public static void main(String[] args) {
    launch();
  }
}