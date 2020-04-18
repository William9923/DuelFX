package com.avatarduel.guicontroller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;

public class FXMLHandler {
    private final String filePath;

    public FXMLHandler(String filePath) {
        this.filePath = filePath;
    }
    public Scene getScene() throws IOException {
        Parent gui = getParent();
        return new Scene(gui);
    }

    public  Parent getParent() throws IOException {
        FXMLLoader loader = getFxmlLoader();
        return loader.load();
    }

    public FXMLLoader getFxmlLoader() throws IOException {
        File guiFile = new File("src/main/resources/com/avatarduel/" + filePath);
        return new FXMLLoader(guiFile.toURI().toURL());
    }
}
