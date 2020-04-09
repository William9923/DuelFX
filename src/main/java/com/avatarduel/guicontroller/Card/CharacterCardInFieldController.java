package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.CharacterState;
import com.google.common.eventbus.EventBus;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CharacterCardInFieldController extends CardController {
    @FXML HBox card_actions;
    @FXML ImageView card_rotate;
    @FXML ImageView card_attack;
    private boolean isActionEnabled;
    private CharacterCardInField characterCardInField;

    public void setCard(CharacterCardInField cardInField) {
        super.setCard(cardInField.getCard());
        this.characterCardInField = cardInField;
    }

    @FXML
    public void initialize() {
        card_actions.setVisible(false);
        isActionEnabled = true;
    }

    @FXML
    public void rotateCard() {
        if(characterCardInField.getPosition() == CharacterState.ATTACK) {
            card_border.rotateProperty().setValue(90);
            card_attack.setVisible(false);
        }
        else{
            card_border.rotateProperty().setValue(0);
            card_attack.setVisible(true);
        }
        characterCardInField.switchPosition();
    }

    // dipake FieldController untuk membuat action tidak terlihat ketika di hover
    public void setActionVisible(boolean value) {
        isActionEnabled = false;
    }

    @FXML
    public void showActions() {
        if(!card_name.getText().equals("") && isActionEnabled) {
            card_actions.setVisible(true);
        }
    }

    @FXML
    public void hideActions() {
        card_actions.setVisible(false);
    }

    @FXML
    public void cardAttack() {
        Game.getInstance().getEventBus().post(this.characterCardInField);
    }

    public CharacterCardInField getCharacterCardInField() {
        return characterCardInField;
    }
}
