package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Card.CardOnPlayController;
import com.avatarduel.model.card.Card;
import javafx.fxml.FXML;

import java.util.HashMap;
import java.util.Map;

public class FieldController {
    private Map<String, Card> CardsOnPlay;

    @FXML
    CardOnPlayController card1Controller;
    @FXML CardOnPlayController card2Controller;
    @FXML CardOnPlayController card3Controller;
    @FXML CardOnPlayController card4Controller;
    @FXML CardOnPlayController card5Controller;
    @FXML CardOnPlayController card6Controller;
    @FXML CardOnPlayController card7Controller;
    @FXML CardOnPlayController card8Controller;
    @FXML CardOnPlayController card9Controller;
    @FXML CardOnPlayController spell1Controller;
    @FXML CardOnPlayController spell2Controller;
    @FXML CardOnPlayController spell3Controller;
    @FXML CardOnPlayController spell4Controller;
    @FXML CardOnPlayController spell5Controller;
    @FXML CardOnPlayController spell6Controller;
    @FXML CardOnPlayController spell7Controller;
    @FXML CardOnPlayController spell8Controller;
    @FXML CardOnPlayController spell9Controller;

    @FXML
    public void initialize() {
        this.CardsOnPlay = new HashMap<String, Card>();

    }
}
