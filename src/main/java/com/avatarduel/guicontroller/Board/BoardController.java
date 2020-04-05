package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Card.DisplayCardController;
import com.avatarduel.model.card.CharacterCard;
import javafx.fxml.FXML;

public class BoardController {
    @FXML private DisplayCardController selectedController;
    @FXML private FieldController fieldAController;

    public BoardController() {
        System.out.println("Board GUI constructor");
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void setData(CharacterCard card) {
        selectedController.setCard(card);
    }

    public void setSelectedCard(CharacterCard card) {
        setData(card);
    }
}
