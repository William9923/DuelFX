package com.avatarduel.guicontroller.Card;

import com.avatarduel.guicontroller.Request.ShowSelectedCardRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.IField;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public abstract class CardInFieldController extends CardController {
    protected PlayerType playerType;
    protected boolean isActionEnabled;

    @FXML VBox card_actions;

    @FXML
    public void initialize() {
        card_actions.setVisible(false);
        isActionEnabled = true;
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

    public void showSelectedCard() {
        if(cardData != null && this.playerType == Game.getInstance().getCurrentPlayer()) {
            Game.getInstance().getEventBus().post(new ShowSelectedCardRequest(this.cardData));
        }
    }
}
