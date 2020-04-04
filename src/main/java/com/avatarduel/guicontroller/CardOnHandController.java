package com.avatarduel.guicontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class CardOnHandController {
    @FXML private VBox container;
    @FXML private Label card_name;
    @FXML private ImageView card_img;
    @FXML private Label card_att;
    @FXML private Label card_def;
    @FXML private Label card_pow;

    public void flipCard() {
        card_name.setVisible(false);
        card_img.setVisible(false);
        card_att.setVisible(false);
        card_def.setVisible(false);
        card_pow.setVisible(false);
        container.getStyleClass().add("flipped_card");
    }
}
