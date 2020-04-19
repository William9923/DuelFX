package com.avatarduel.guicontroller.Card;

import com.avatarduel.guicontroller.Request.GlobalRequest.ShowSelectedCardRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * used for controlling the card in field's actions, has 2 children : skillcardinfield and charactercardinfield
 */
public abstract class CardInFieldController extends CardController {
    protected PlayerType playerType;
    protected boolean isActionEnabled;

    @FXML VBox card_actions;

    /**
     * set the actions not visible
     */
    @FXML
    public void initialize() {
        card_actions.setVisible(false);
        isActionEnabled = true;
    }

    /**
     * used by fieldController to set the actions visible or not depends on current player
     * @param value if the value is true, then you can do actions
     */
    public void setActionVisible(boolean value) {
        isActionEnabled = value;
    }

    /**
     * show the card actions
     */
    @FXML
    public void showActions() {
        if(!card_name.getText().equals("") && isActionEnabled) {
            card_actions.setVisible(true);
        }
    }

    /**
     * hide the card actions
     */
    @FXML
    public void hideActions() {
        card_actions.setVisible(false);
    }

    /**
     * if the playertype is current player , then show card to board
     */
    @FXML
    public void showSelectedCard() {
        if(cardData != null && this.playerType == Game.getInstance().getCurrentPlayer()) {
            Game.getInstance().getEventBus().post(new ShowSelectedCardRequest(this.cardData));
        }
    }
}
