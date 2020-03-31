package com.avatarduel.guicontroller;

import com.avatarduel.model.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CardGUI {
    private Card data;
    @FXML private AnchorPane card_border;
    @FXML private Label card_name;
    @FXML private ImageView card_img;
    @FXML private Label card_desc;
    @FXML private HBox card_attributes;
    @FXML private Label card_atk;
    @FXML private Label card_def;
    @FXML private Label card_pow;

    public void setData(Card card) {
        this.data = data;
        card_name.setText(this.data.getName());
    }
    public void coba2() {
        card_name.setText("aaa");
    }
    @FXML
    public void initialize() {
        card_name.setText("hehe");
    }
}
