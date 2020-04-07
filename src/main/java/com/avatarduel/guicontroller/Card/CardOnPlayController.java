package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.card.CharacterCardInField;
import javafx.fxml.FXML;

public class CardOnPlayController extends CardController {
    private boolean onAttackPosition;

    @FXML
    public void initialize() {
        this.onAttackPosition = true;
    }

    public void rotateCard() {
        this.card_border.rotateProperty().setValue(90);
    }
}
