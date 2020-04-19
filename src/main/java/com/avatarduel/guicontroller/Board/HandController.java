package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Card.CardInHandController;
import com.avatarduel.guicontroller.Request.SpecificRequest.HandRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.player_component.Hand;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class HandController{
    private PlayerType playerType;
    private List<CardInHandController> cards;
    private boolean isFlipped;

    @FXML
    CardInHandController card1Controller;
    @FXML
    CardInHandController card2Controller;
    @FXML
    CardInHandController card3Controller;
    @FXML
    CardInHandController card4Controller;
    @FXML
    CardInHandController card5Controller;
    @FXML
    CardInHandController card6Controller;
    @FXML
    CardInHandController card7Controller;
    @FXML
    CardInHandController card8Controller;
    @FXML
    CardInHandController card9Controller;
    @FXML
    CardInHandController card10Controller;

    public HandController() {
    }

    @FXML
    public void initialize() {
        Game.getInstance().getEventBus().register(this);
        cards = new ArrayList<CardInHandController>();
        cards.add(card1Controller);
        cards.add(card2Controller);
        cards.add(card3Controller);
        cards.add(card4Controller);
        cards.add(card5Controller);
        cards.add(card6Controller);
        cards.add(card7Controller);
        cards.add(card8Controller);
        cards.add(card9Controller);
        cards.add(card10Controller);
        for(int i = 0 ; i < 10 ; i++) {
            cards.get(i).setNullCard();
        }
    }

    public void setPlayerTypeAndRender(PlayerType playerType) {
        this.playerType = playerType;
        this.isFlipped = false;
        for(CardInHandController cardInHandController : cards) {
            cardInHandController.setPlayerType(playerType);
        }
        this.render();
    }

    public void render() {
        if (!this.isFlipped) {
            for(int i = 0 ; i < 10 ; i++) {
                cards.get(i).setNullCard();
            }
            Hand currentHand = Game.getInstance().getPlayerByType(this.playerType).getHand();

            int i = 0;
            for(Card cardInHand: currentHand) {
                cards.get(i).setCard(cardInHand);
                i++;
            }
            while(i < 10) {
                cards.get(i).setNullCard();
                i++;
            }
        }
    }

    public void flipCards() {
        for(CardInHandController cardInHandController : cards) {
            if(cardInHandController.getCardData() != null) {
                cardInHandController.flipCard();
            }
        }
        this.isFlipped = !this.isFlipped;
    }

    @Subscribe
    public void update(HandRenderRequest request) {
        this.render();
    }
}
