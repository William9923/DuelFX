package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.avatarduel.factory.CardFactory;
import com.avatarduel.guicontroller.Board.BoardController;
import com.avatarduel.guicontroller.Card.DisplayCardController;
import com.avatarduel.guicontroller.MainMenu.MainMenuController;
import com.avatarduel.model.card.LandCard;
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
import javafx.stage.Stage;

public class AvatarDuel extends Application {

  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String CHARACTER_CSV_FILE_PATH ="card/data/character.csv";
  private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
//    Parent root = FXMLLoader.load(getClass().getResource("Board.fxml"));
    // Load Main Menu
    FXMLLoader mainmenuLoader = getMainMenu();
    Parent mainmenuGUI = mainmenuLoader.load();
    Scene mainmenu = new Scene(mainmenuGUI);
    MainMenuController mainMenuController = mainmenuLoader.getController();

    // Load list of cards
    List<String[]> characterRows = getSkillAuras();
    Button seeCardBtn = new Button("see card");
    seeCardBtn.setLayoutX(500);
    seeCardBtn.setLayoutY(100);
    Group root = new Group();

    FXMLLoader cardLoader = getCardGUI();
    Parent cardGUIBox = cardLoader.load();
    root.getChildren().addAll(seeCardBtn, cardGUIBox);
    DisplayCardController controller = cardLoader.getController();

    seeCardBtn.setOnAction(new EventHandler<ActionEvent>() {
      int i = 1;
      @Override
      public void handle(ActionEvent event) {
        root.getChildren().remove(cardGUIBox);
        String[] character;
        try {
          character = characterRows.get(i);
        }
        catch (IndexOutOfBoundsException E) {
          i = 1;
          character = characterRows.get(i);
        }
        i++;
        //id	name	element	description	imagepath	power	attack	defense
        SkillAuraCard card = new SkillAuraCard(Integer.parseInt(character[0]),character[1], CardFactory.outputElement(character[2]),character[3],character[4],Integer.parseInt(character[5]),Integer.parseInt(character[6]),Integer.parseInt(character[7]));
        controller.setCard(card);
        root.getChildren().add(cardGUIBox);
      }
    });

    // load board
    FXMLLoader FXMLBoardGUI = new FXMLLoader(getClass().getResource("GUI/Board/Board.fxml"));
    Parent boardGUI = FXMLBoardGUI.load();
    Scene board = new Scene(boardGUI);
    BoardController boardController = FXMLBoardGUI.getController();


    stage.setTitle("Avatar Duel");
    stage.setScene(mainmenu);
    stage.setFullScreen(true);
    stage.show();

    mainMenuController.setStartOnScene(stage, board);
    mainMenuController.setCardsOnScene(stage, new Scene(root));
  }

  private FXMLLoader getMainMenu() {
    return new FXMLLoader(getClass().getResource("GUI/MainMenu/MainMenu.fxml"));
  }

  public FXMLLoader getCardGUI() {
    return new FXMLLoader(getClass().getResource("GUI/Card/CardGUI.fxml"));
  }



  public List<String[]> getSkillAuras() throws IOException, URISyntaxException {
    File skillAuraFile = new File(getClass().getResource(SKILL_AURA_CSV_FILE_PATH).toURI());
    CSVReader skillAuraReader = new CSVReader(skillAuraFile, "\t");
    return skillAuraReader.read();
  }

  public List<String[]> getCharacters() throws IOException, URISyntaxException {
    File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
    return characterReader.read();
  }

  public List<String[]> getLands() throws IOException, URISyntaxException {
    File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
    CSVReader LandCSVFile = new CSVReader(landCSVFile, "\t");
    return LandCSVFile.read();
  }

  public static void main(String[] args) {
    launch();
  }
}