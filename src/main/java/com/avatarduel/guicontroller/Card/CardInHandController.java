package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.SummonEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Board.HandController;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardInHand;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class CardInHandController extends CardController{
    @FXML private Button card_play;
    private boolean isFlipped;
    private String borderStyle;
    private PlayerType playerType;
    private HandController handController;

    @FXML
    public void initialize() {
        card_play.setVisible(false);
        isFlipped = false;
        borderStyle = "null_card";
    }

    @Override
    public void setCard(Card card) {
        super.setCard(card);
        switch (cardData.getElement()) {
            case WATER:
                borderStyle = "water_border";
                break;
            case FIRE:
                borderStyle = "fire_border";
                break;
            case EARTH:
                borderStyle = "earth_border";
                break;
            case AIR:
                borderStyle = "air_border";
                break;
        }
    }

    public void flipCard() {
        // kalo udah di flip, buka
        card_name.setVisible(isFlipped);
        card_icon.setVisible(isFlipped);
        card_img.setVisible(isFlipped);
        card_atk.setVisible(isFlipped);
        card_def.setVisible(isFlipped);
        card_pow.setVisible(isFlipped);
        super.removeAllBorderStyle();
        if(isFlipped) {
            super.addBorderStyle(borderStyle);
        }
        else {
            super.addBorderStyle("flipped_card");
        }
        System.out.println("Player card " + playerType + " has border style : " + card_border.getStyleClass());
        isFlipped = !isFlipped;
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