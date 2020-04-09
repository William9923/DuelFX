package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.IEvent;
import com.avatarduel.event.PlayLandCardEvent;
import com.avatarduel.event.SummonEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Board.HandController;
import com.avatarduel.guicontroller.Board.PlayerStatusController;
import com.avatarduel.guicontroller.Request.ShowSelectedCardRequest;
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

    @FXML
    public void initialize() {
        card_play.setVisible(false);
        borderStyle = "null_card";
    }

    @Override
    public void setCard(Card card) {
        super.setCard(card);
        borderStyle = card_border.getStyleClass().get(0);
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
        if(playerType == Game.getInstance().getCurrentPlayer()) {
            card_play.setVisible(true);
        }
    }

    @FXML
    public void hidePlayButton() {
        card_play.setVisible(false);
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    @FXML
    public void playIsClicked() {
        try {
            IEvent playCardEvent;
            if(cardData.getType() == CardType.LAND) {
                playCardEvent = new PlayLandCardEvent(cardData.getId(), playerType);
                playCardEvent.execute();
            }
            else if(cardData.getType() == CardType.CHARACTER) {
                playCardEvent = new SummonEvent(cardData.getId(), playerType);
                playCardEvent.execute();
            }
            else {
                // TODO : BIKIN IMPLEMENTASI PLAY BUAT SKILL
            }
            Game.getInstance().getGUIRenderServer().renderAll(Channel.getChannelFromPlayerType(playerType));
        }
        catch(InvalidOperationException e) {
            System.out.println(e.getOperation());
            System.out.println(e.getMessage());
        }
    }

    public void showSelectedCard() {
        if(cardData != null && this.playerType == Game.getInstance().getCurrentPlayer()) {
            Game.getInstance().getEventBus().post(new ShowSelectedCardRequest(this.cardData));
        }
    }
}