package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.card.CharacterCardInField;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class CharacterCardOnPlayController extends CardController {
    @FXML HBox card_actions;


    public void setCard(CharacterCardInField cardInField) {
        super.setCard(cardInField.getCard());
    }

    @FXML
    public void initialize() {
        card_actions.setVisible(false);
    }

    @FXML
    public void showActions() {
        card_actions.setVisible(true);
    }

    @FXML
    public void hideActions() {
        card_actions.setVisible(false);
    }
}
