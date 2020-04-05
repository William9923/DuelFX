package com.avatarduel.guicontroller;

import com.avatarduel.model.card.CharacterCard;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class BoardController {
    @FXML private CardController selectedController;

    public BoardController() {
        System.out.println("Board GUI constructor");
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void setData(CharacterCard card) {
        selectedController.setData(card);
    }

    public void setSelectedCard(CharacterCard card) {
        setData(card);
    }
}
