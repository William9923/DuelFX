package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.avatarduel.guicontroller.CardGUI;
import com.avatarduel.model.Card;
import com.avatarduel.model.CharacterCard;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.avatarduel.model.Element;
import com.avatarduel.model.Land;
import com.avatarduel.util.CSVReader;

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
//    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    Text text = new Text();
    text.setText("Loading...");
    text.setX(50);
    text.setY(50);

    Text text2 = new Text();
    text2.setText("Canda hehe");
    text2.setX(100);
    text2.setY(100);

    Button btn = new Button("see card");

    Group root = new Group();
    root.getChildren().addAll(text);
    root.getChildren().add(text2);
    root.getChildren().add(btn);

    Scene scene = new Scene(root, 1280, 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

    File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
    List<String[]> characterRows = characterReader.read();
    String[] firstCharacter = characterRows.get(16);
    CharacterCard card = new CharacterCard(firstCharacter[0],firstCharacter[1],firstCharacter[2],firstCharacter[3],firstCharacter[4],firstCharacter[5], firstCharacter[6], firstCharacter[7]);

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/CardGUI.fxml"));
      Parent cardGUIBox = loader.load();
      CardGUI controller = loader.getController();
      controller.setData(card);
      Scene cardGUIScene = new Scene(cardGUIBox);
      btn.setOnAction(e -> {
        stage.setScene(cardGUIScene);
      });
    } catch (IOException IOE) {
      System.out.println("gasabi bray");
    }
    try {
      this.loadCards();
      text.setText("IU cinta Arthur");
      System.out.println("Successful LoadTime");
    } catch (Exception e) {
      System.out.println("Unsuccessful LoadTime");
    }
  }


  public static void main(String[] args) {
    launch();
  }
}