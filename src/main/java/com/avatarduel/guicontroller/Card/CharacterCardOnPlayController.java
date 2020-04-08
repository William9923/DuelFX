package com.avatarduel.guicontroller.Card;

import com.avatarduel.guicontroller.Board.FieldController;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CharacterCardOnPlayController extends CardController {
    @FXML HBox card_actions;
    @FXML ImageView card_rotate;
    @FXML ImageView card_attack;
    private CharacterCardInField characterCardInField;
    private FieldController fieldController;
    private PlayerType playerType;
    private int index;

    public void setCard(CharacterCardInField cardInField) {
        super.setCard(cardInField.getCard());
        this.characterCardInField = cardInField;
    }

    private boolean onAttackPosition;

    @FXML
    public void initialize() {
        card_actions.setVisible(false);
        this.onAttackPosition = true;
    }

    @FXML
    public void postAttackRequest() {
        fieldController.showAttackRequestForm(new CharacterCardInField((CharacterCard) cardData, CharacterState.ATTACK, 1, index));
    }

    @FXML
    public void rotateCard() {
        if(onAttackPosition) {
            card_border.rotateProperty().setValue(90);
            card_attack.setVisible(false);
        }
        else{
            card_border.rotateProperty().setValue(0);
            card_attack.setVisible(true);
        }
        onAttackPosition = !onAttackPosition;
    }

    @FXML
    public void showActions() {
        if(!card_name.getText().equals("") && Game.getInstance().getCurrentPlayer() == playerType) {
            card_actions.setVisible(true);
        }
    }

    @FXML
    public void hideActions() {
        card_actions.setVisible(false);
    }

    public void setFieldController(FieldController fieldController) {
        this.fieldController = fieldController;
    }

    public FieldController getFieldController() {
        return fieldController;
    }

    public CharacterCardInField getCharacterCardInField() {
        return characterCardInField;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
