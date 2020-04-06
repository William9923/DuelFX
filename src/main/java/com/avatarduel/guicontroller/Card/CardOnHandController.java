package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.card.CharacterCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class CardOnHandController extends CardController{
    @FXML private Button card_play;
    @FXML
    public void initialize() {
        card_play.setVisible(false);
    }
    public void flipCard() {
        if(isCardFlipped()) {
            card_name.setVisible(true);
            card_name.setVisible(true);
            card_img.setVisible(true);
            card_atk.setVisible(true);
            card_def.setVisible(true);
            card_pow.setVisible(true);
        }
        else {
            card_name.setVisible(false);
            card_img.setVisible(false);
            card_atk.setVisible(false);
            card_def.setVisible(false);
            card_pow.setVisible(false);
            super.setBorderStyle("flipped_card");
        }
    }

    @FXML
    public void showPlayButton() {
        card_play.setVisible(true);
    }

    @FXML
    public void hidePlayButton() {
        card_play.setVisible(false);
    }

    public boolean isCardFlipped() {
        return card_border.getStyleClass().contains("flipped_card");
    }
}