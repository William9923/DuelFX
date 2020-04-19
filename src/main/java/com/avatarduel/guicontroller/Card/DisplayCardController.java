package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.card.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
/**
 * used to for controlling the displayed card in the boardcontroller
 * @author G10-K03-CardGameOOP
 */
public class DisplayCardController extends CardController {
    @FXML private Label card_desc;

    /**
     * {@inheritDoc}
     * @param card the card
     *             add the description to display
     */
    @Override
    public void setCard(Card card) {
        super.setCard(card);
        card_desc.setText(this.cardData.getDescription());
    }
}
