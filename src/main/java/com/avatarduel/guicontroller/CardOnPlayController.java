package com.avatarduel.guicontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CardOnPlayController {
    @FXML private Label card_name;
    @FXML private ImageView card_img;
    @FXML private Label card_att;
    @FXML private Label card_def;
    @FXML private Label card_pow;

    @FXML
    public void initialize() {
        card_name.setText("huhuhu");
    }
}
