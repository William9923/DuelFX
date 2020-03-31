package com.avatarduel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CardGUI {
    public VBox get() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/CardGUI.fxml"));
        Parent Card = loader.load();

        return new VBox(Card);
    }

}
