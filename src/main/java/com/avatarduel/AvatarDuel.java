package com.avatarduel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;

import com.avatarduel.model.Element;
import com.avatarduel.model.Land;
import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";

  public void loadCards() throws IOException, URISyntaxException {
    File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
    CSVReader landReader = new CSVReader(landCSVFile, "\t");
    landReader.setSkipHeader(true);
    List<String[]> landRows = landReader.read();
    for (String[] row : landRows) {
      Land l = new Land(row[1], row[3], Element.valueOf(row[2]));
    }
  }

  @Override
  public void start(Stage stage) {
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

    Scene scene = new Scene(root, 1280, 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
    try {
      Scene cardGUI = this.getCardGUI();
      btn.setOnAction(e -> {
        stage.setScene(cardGUI);
      });
    } catch (IOException IOE) {
      System.out.println("error in file input stream");
    }
    try {
      this.loadCards();
      text.setText("IU cinta Arthur");
      System.out.println("Successful LoadTime");
    } catch (Exception e) {
      System.out.println("Unsuccessful LoadTime");
    }
  }
  private Scene getCardGUI() throws IOException {
    // ini masi belum bisa, gatau knp
    FXMLLoader loader = new FXMLLoader();
    String fxmlDocPath = "C:\\Users\\Asus\\Documents\\GitHub\\CardGameOOP\\src\\main\\java\\com\\avatarduel\\CardGUI.fxml";
    FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
    VBox Card = loader.load(fxmlStream);
    return new Scene(Card);
  }

  public static void main(String[] args) {
    launch();
  }
}