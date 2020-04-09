package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.IEvent;
import com.avatarduel.event.PlayLandCardEvent;
import com.avatarduel.event.SummonEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Board.HandController;
import com.avatarduel.guicontroller.Board.PlayerStatusController;
import com.avatarduel.guicontroller.Server.Channel;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class CardInHandController extends CardController{
    @FXML private Button card_play;
    private String borderStyle;
    private PlayerType playerType;
    private HandController handController;
    private PlayerStatusController playerStatusController;

    @FXML
    public void initialize() {
        card_play.setVisible(false);
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
        boolean isFlipped = Game.getInstance().getCurrentPlayer() == playerType;
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
            IEvent playCardEvent;
            if(cardData.getType() == CardType.LAND) {
                playCardEvent = new PlayLandCardEvent(cardData.getId(), playerType);
                playCardEvent.execute();
                gameServer.renderAll(Channel.getChannelFromPlayerType(playerType));
            }
            else if(cardData.getType() == CardType.CHARACTER) {
                playCardEvent = new SummonEvent(cardData.getId(), playerType);
                playCardEvent.execute();
                gameServer.renderAll(Channel.getChannelFromPlayerType(playerType));
            }
            else {
                // TODO : BIKIN IMPLEMENTASI PLAY BUAT SKILL
            }
        }
        catch(InvalidOperationException e) {
            System.out.println(e.getOperation());
            System.out.println(e.getMessage());
        }
    }
}