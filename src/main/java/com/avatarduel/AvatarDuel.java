package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
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
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

//    Text text = new Text();
//    text.setText("Loading...");
//    text.setX(50);
//    text.setY(50);
//
//    Text text2 = new Text();
//    text2.setText("Bitch");
//    text2.setX(100);
//    text2.setY(100);
//
//    Group root = new Group();
//    root.getChildren().add(text);
//    root.getChildren().add(text2);

    Scene scene = new Scene(root, 1280, 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

    try {
      this.loadCards();
      System.out.println("Successful LoadTime");
    } catch (Exception e) {
      System.out.println("Unsuccessful LoadTime");
    }
  }

  public static void main(String[] args) {
    launch();
  }
}