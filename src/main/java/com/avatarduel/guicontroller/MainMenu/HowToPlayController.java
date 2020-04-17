package com.avatarduel.guicontroller.MainMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HowToPlayController {
    private Parent page1;
    private Parent page2;
    private Parent page3;
    private Parent page4;
    private Popup howToPlay;
    private Stage stage;

    public HowToPlayController() {
        howToPlay = new Popup();

        try {
            this.setUpPages();
            howToPlay.getContent().add(page1);
//            howToPlay.show();
        }
        catch (IOException IOE) {
            howToPlay.getContent().add(new Label("HowToPlayController : Tutorial scene not loaded"));
        }
    }

    private void setUpPages() throws IOException {
        FXMLLoader page1Loader = getLoader("GUI/MainMenu/HowToPlay/Page1.fxml");
        FXMLLoader page2Loader = getLoader("GUI/MainMenu/HowToPlay/Page2.fxml");
        FXMLLoader page3Loader = getLoader("GUI/MainMenu/HowToPlay/Page3.fxml");
        FXMLLoader page4Loader = getLoader("GUI/MainMenu/HowToPlay/Page4.fxml");
        this.page1 = page1Loader.load();
        this.page2 = page2Loader.load();
        this.page3 = page3Loader.load();
        this.page4 = page4Loader.load();
        page1Loader.setController(this);
        page2Loader.setController(this);
        page3Loader.setController(this);
        page4Loader.setController(this);
    }

    public void goToPage1() {
        howToPlay.getContent().remove(0);
        howToPlay.getContent().add(this.page1);

        Node nextButton = this.page1.getScene().lookup("#nextButton");
        nextButton.setOnMouseClicked(e -> {
            this.goToPage2();
        });
        Node exitButton = this.page1.getScene().lookup("#exitButton");
        exitButton.setOnMouseClicked(e -> {
            this.exit();
        });
        howToPlay.show(this.stage);
    }

    public void goToPage2() {
        howToPlay.getContent().remove(0);
        howToPlay.getContent().add(this.page2);
        Node nextButton = this.page2.getScene().lookup("#nextButton");
        nextButton.setOnMouseClicked(e -> {
            this.goToPage3();
        });
        Node exitButton = this.page2.getScene().lookup("#exitButton");
        exitButton.setOnMouseClicked(e -> {
            this.exit();
        });
        howToPlay.show(this.stage);

    }

    public void goToPage3() {
        howToPlay.getContent().remove(0);
        howToPlay.getContent().add(this.page3);
        Node nextButton = this.page3.getScene().lookup("#nextButton");
        nextButton.setOnMouseClicked(e -> {
            this.goToPage4();
        });
        Node exitButton = this.page3.getScene().lookup("#exitButton");
        exitButton.setOnMouseClicked(e -> {
            this.exit();
        });
        howToPlay.show(this.stage);
    }

    public void goToPage4() {
        howToPlay.getContent().remove(0);
        howToPlay.getContent().add(this.page4);
        Node exitButton = this.page4.getScene().lookup("#exitButton");
        exitButton.setOnMouseClicked(e -> {
            this.exit();
        });
        howToPlay.show(this.stage);
    }

    @FXML
    public void exit() {
        this.howToPlay.hide();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public FXMLLoader getLoader(String filePath) throws IOException {
        File guiFile = new File("src/main/resources/com/avatarduel/" + filePath);
        return new FXMLLoader(guiFile.toURI().toURL());
    }
}
