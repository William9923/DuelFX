package com.avatarduel.guicontroller.Board;

import com.avatarduel.factory.CardFactory;
import com.avatarduel.guicontroller.Card.CardOnHandController;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.SkillAuraCard;
import com.avatarduel.model.player_component.Deck;
import com.avatarduel.model.type.CardType;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandController {
    private Deck deck;
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

//    public HandController(List<Card> cards) {
//        this.cards = cards;
//    }

    @FXML
    public void initialize() {
        deck = new Deck(50);

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
            Card card = deck.draw();
            cards.get(i).setCard(card);
//            if(card.getType().equals(CardType.CHARACTER)) {
//                CharacterCard characterCard = (CharacterCard) card;
//                cards.get(i).setCard(characterCard);
//            }
//            else if (card.getType().equals(CardType.SKILL_AURA)) {
//                SkillAuraCard skillAuraCard = (SkillAuraCard) card;
//                cards.get(i).setCard(skillAuraCard);
//            }
//            else if (card.getType().equals(CardType.LAND)) {
//                LandCard landCard = (LandCard) card;
//                cards.get(i).setCard(landCard);
//            }
        }
    }
}
