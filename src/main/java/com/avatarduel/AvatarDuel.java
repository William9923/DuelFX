package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.avatarduel.guicontroller.BoardController;
import com.avatarduel.guicontroller.CardController;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.util.CSVReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String CHARACTER_CSV_FILE_PATH ="card/data/character.csv";

  public void loadCards() throws IOException, URISyntaxException {
    File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
    CSVReader landReader = new CSVReader(landCSVFile, "\t");
    landReader.setSkipHeader(true);
    List<String[]> landRows = landReader.read();
  }

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
//    Parent root = FXMLLoader.load(getClass().getResource("Board.fxml"));

    Button seeCardBtn = new Button("see card");
    seeCardBtn.setLayoutX(500);
    seeCardBtn.setLayoutY(100);

    Button seeBoardBtn = new Button("see board");
    seeBoardBtn.setLayoutX(500);

    Button seeMainMenuBtn = new Button("main menu");
    seeMainMenuBtn.setLayoutX(500);
    seeMainMenuBtn.setLayoutY(200);

    Group root = new Group();
    root.getChildren().addAll(seeCardBtn, seeBoardBtn, seeMainMenuBtn);

    Scene scene = new Scene(root, 1280, 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

    File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
    List<String[]> characterRows = characterReader.read();

    FXMLLoader FXMLBoardGUI = new FXMLLoader(getClass().getResource("GUI/Board/Board.fxml"));
    Parent boardGUI = FXMLBoardGUI.load();
    Scene board = new Scene(boardGUI);
    BoardController boardController = FXMLBoardGUI.getController();
    seeBoardBtn.setOnAction(e -> {
      stage.setScene(board);
    });

    FXMLLoader mainmanuLoader = new FXMLLoader(getClass().getResource("GUI/MainMenu/MainMenu.fxml"));
    Parent mainmenuGUI = mainmanuLoader.load();
    Scene mainmenu = new Scene(mainmenuGUI);
    seeMainMenuBtn.setOnAction(e -> {
      stage.setScene(mainmenu);
      stage.setFullScreen(true);

    });

    try {
      FXMLLoader loader = getCardGUI();
      Parent cardGUIBox = loader.load();
      root.getChildren().add(cardGUIBox);
      CardController controller = loader.getController();
      seeCardBtn.setOnAction(new EventHandler<ActionEvent>() {
        int i = 1;
        @Override
        public void handle(ActionEvent event) {
          root.getChildren().remove(cardGUIBox);
          String[] character = characterRows.get(i);
          i++;
          CharacterCard card = new CharacterCard(character[0],character[1],character[2],character[3],character[4],character[5], character[6], character[7]);
          System.out.println(card.getElement());
          controller.setData(card);
          boardController.setSelectedCard(card);
          root.getChildren().add(cardGUIBox);
        }
      });
    } catch (IOException IOE) {
      System.out.println("gasabi bray");
    }
  }

  public FXMLLoader getCardGUI() {
    return new FXMLLoader(getClass().getResource("GUI/Card/CardGUI.fxml"));
  }
  public static void main(String[] args) {
    launch();
  }
}