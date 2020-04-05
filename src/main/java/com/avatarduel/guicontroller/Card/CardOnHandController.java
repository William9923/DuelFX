package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.card.CharacterCard;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class CardOnHandController extends CardController{
    @FXML private VBox container;

    public void flipCard() {
        card_name.setVisible(false);
        card_img.setVisible(false);
        card_atk.setVisible(false);
        card_def.setVisible(false);
        card_pow.setVisible(false);
        container.getStyleClass().add("flipped_card");
    }

    public void setNull() {
        card_name = null;
        card_img = null;
        card_atk = null;
        card_def = null;
        card_pow = null;
        container.getStyleClass().remove("flipped_card");
    }
}
