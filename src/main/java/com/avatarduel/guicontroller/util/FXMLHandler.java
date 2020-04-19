package com.avatarduel.guicontroller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;

/**
 * class to handle loading the fxml
 */
public class FXMLHandler {
    private final String filePath;

    /**
     * initialize the file path
     * @param filePath the file path to specified fxml, starting
     *                 after src/main/resources/com/avatarduel/
     * example : to get board.fxml, filepath is "GUI/Board/Board.fxml"
     */
    public FXMLHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * get the scene of the file path
     * @return the scene
     * @throws IOException if there is no such file
     */
    public Scene getScene() throws IOException {
        Parent gui = getParent();
        return new Scene(gui);
    }

    /**
     * get the node parent from fxml, by using method fxmlloader.load()
     * @return the node parent
     * @throws IOException if there is no such file
     */
    public  Parent getParent() throws IOException {
        FXMLLoader loader = getFxmlLoader();
        return loader.load();
    }

    /**
     * load the file, and create a fxmlloader for the file
     * @return fxml file loader
     * @throws IOException if there is no such file
     */
    public FXMLLoader getFxmlLoader() throws IOException {
        File guiFile = new File("src/main/resources/com/avatarduel/" + filePath);
        return new FXMLLoader(guiFile.toURI().toURL());
    }
}
