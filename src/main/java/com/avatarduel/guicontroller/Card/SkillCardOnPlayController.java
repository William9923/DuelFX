package com.avatarduel.guicontroller.Card;

import com.avatarduel.guicontroller.Board.FieldController;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;

public class SkillCardOnPlayController extends CardController {
    private PlayerType playerType;
    private int index;
    private FieldController fieldController;

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setFieldController(FieldController fieldController) {
        this.fieldController = fieldController;
    }

    public FieldController getFieldController() {
        return fieldController;
    }
}
