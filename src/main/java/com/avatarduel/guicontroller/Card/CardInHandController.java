package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.SummonEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Board.HandController;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CardInHand;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class CardInHandController extends CardController{
    @FXML private Button card_play;
    private int index;
    private PlayerType playerType;
    private HandController handController;

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

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isCardFlipped() {
        return card_border.getStyleClass().contains("flipped_card");
    }

    public void setHandController(HandController handController) {
        this.handController = handController;
    }

    @FXML
    public void playIsClicked() {
        try {
            SummonEvent summonEvent = new SummonEvent(cardData.getId(), this.playerType, CharacterState.ATTACK);
            summonEvent.execute();
            handController.render();
            handController.getCorrespondingField().render();
        }
        catch(InvalidOperationException e) {
            System.out.println(e.getOperation());
            System.out.println(e.getMessage());
        }
    }
}