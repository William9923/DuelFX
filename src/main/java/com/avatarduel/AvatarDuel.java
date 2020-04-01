package com.avatarduel;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.guicontroller.CardGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String CHARACTER_CSV_FILE_PATH ="card/data/character.csv";


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
    root.getChildren().add(text);
    root.getChildren().add(text2);
    root.getChildren().add(btn);

    Scene scene = new Scene(root, 1280, 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
//    File characterCSVFile;
//    try {
//      characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
//    }
//    catch (Exception E){
//      System.out.println("Bagian ini gabisa");
//      return;
//    }
//
//    CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
//    characterReader.setSkipHeader(true);
//    List<String[]> characterRows = characterReader.read();
//    String[] firstCharacter = characterRows.get(0);
//    //0.id	1.name	2.element	3.description	4.imagepath	5.attack	6.defense	7.power
//    Card card = new CharacterCard(firstCharacter[0],firstCharacter[1],firstCharacter[2],firstCharacter[3],firstCharacter[4],firstCharacter[5], firstCharacter[6], firstCharacter[7]);


    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/CardGUI.fxml"));
      Parent cardGUIBox = loader.load();
      CardGUI controller = loader.getController();
      controller.coba2();
      Scene cardGUIScene = new Scene(cardGUIBox);
      btn.setOnAction(e -> {
        stage.setScene(cardGUIScene);
      });
    } catch (IOException IOE) {
      System.out.println("gasabi bray");
    }
    try {
//      this.loadCards();
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