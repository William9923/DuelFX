package com.avatarduel.guicontroller.Card;

import com.avatarduel.guicontroller.Board.FieldController;
import com.avatarduel.guicontroller.Request.ShowSelectedCardRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;

public class SkillCardInFieldController extends CardInFieldController {
    private SkillCardInField skillCardInField;
    private FieldController fieldController;

    @FXML
    public void initialize() {

    }

    public void setCard(SkillCardInField cardInField) {
        super.setCard(cardInField.getCard());
        this.skillCardInField = cardInField;
    }

    public void removeCard() {

    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
