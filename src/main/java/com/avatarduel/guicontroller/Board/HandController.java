package com.avatarduel.guicontroller.Board;

import com.avatarduel.factory.CardFactory;
import com.avatarduel.guicontroller.Card.CardOnHandController;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.SkillAuraCard;
import com.avatarduel.model.player_component.Deck;
import com.avatarduel.model.player_component.Hand;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandController {
    private PlayerType playerType;
    private List<CardOnHandController> cards;
    @FXML CardOnHandController card1Controller;
    @FXML CardOnHandController card2Controller;
    @FXML CardOnHandController card3Controller;
    @FXML CardOnHandController card4Controller;
    @FXML CardOnHandController card5Controller;
    @FXML CardOnHandController card6Controller;
    @FXML CardOnHandController card7Controller;
    @FXML CardOnHandController card8Controller;
    @FXML CardOnHandController card9Controller;
    @FXML CardOnHandController card10Controller;

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    @FXML
    public void initialize() {
        cards = new ArrayList<CardOnHandController>();
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
            cards.get(i).setNull();
        }
    }

    public void render() {
        Hand currentHand = Game.getInstance().getPlayerByType(this.playerType).getHand();
        int i = 0;
        for(Card cardInHand: currentHand) {
            cards.get(i).setCard(cardInHand);
            i++;
        }
        while(i < 10) {
            cards.get(i).setNull();
            i++;
        }
    }

    public void flipCards() {
        for(CardOnHandController cardOnHandController : cards) {
            cardOnHandController.flipCard();
        }
    }
}
