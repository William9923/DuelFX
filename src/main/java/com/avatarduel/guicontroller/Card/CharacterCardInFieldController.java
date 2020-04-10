package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.ChangePositionEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.guicontroller.Request.ShowSelectedCardRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.EventBus;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CharacterCardInFieldController extends CardController {
    @FXML VBox card_actions;
    @FXML ImageView card_rotate;
    @FXML ImageView card_attack;
    private boolean isActionEnabled;
    private PlayerType playerType;
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
        IEvent event = new ChangePositionEvent(playerType, characterCardInField.getCard().getId());
        Game.getInstance().getEventBus().post(event);
        this.renderRotate();
    }

    public void renderRotate() {
        if(characterCardInField.getPosition() == CharacterState.ATTACK) {
            card_border.rotateProperty().setValue(90);
            card_attack.setVisible(false);
        }
        else{
            card_border.rotateProperty().setValue(0);
            card_attack.setVisible(true);
        }
    }

    // dipake FieldController untuk membuat action tidak terlihat ketika di hover
    public void setActionVisible(boolean value) {
        isActionEnabled = value;
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

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public void showSelectedCard() {
        if(cardData != null && this.playerType == Game.getInstance().getCurrentPlayer()) {
            Game.getInstance().getEventBus().post(new ShowSelectedCardRequest(this.cardData));
        }
    }
}
