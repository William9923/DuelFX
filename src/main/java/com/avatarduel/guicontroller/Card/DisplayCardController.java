package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.card.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public class DisplayCardController extends  CardController {
    @FXML private Label card_desc;
    @FXML private HBox card_attributes;

    @Override
    public void setCard(Card card) {
        super.setCard(card);
        card_desc.setText(this.data.getDescription());
    }
}
