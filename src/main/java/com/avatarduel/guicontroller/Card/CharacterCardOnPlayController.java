package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.card.CharacterCardInField;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class CharacterCardOnPlayController extends CardController {
    @FXML HBox card_actions;

    public void setCard(CharacterCardInField cardInField) {
        super.setCard(cardInField.getCard());
    }

    private boolean onAttackPosition;

    @FXML
    public void rotateCard() {
        if(onAttackPosition) {
            System.out.println("card is on defense position now");
            card_border.rotateProperty().setValue(90);
        }
        else{
            System.out.println("card is on attack position now");
            card_border.rotateProperty().setValue(0);
        }
        onAttackPosition = !onAttackPosition;
    }

    @FXML
    public void initialize() {
        card_actions.setVisible(false);
        this.onAttackPosition = true;
    }

    @FXML
    public void showActions() {
        if(! card_name.getText().equals("")) {
            card_actions.setVisible(true);
        }
    }

    @FXML
    public void hideActions() {
        card_actions.setVisible(false);
    }
}
