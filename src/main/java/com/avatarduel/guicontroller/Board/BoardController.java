package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Card.DisplayCardController;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;

public class BoardController {
    @FXML
    private DisplayCardController selectedController;
    @FXML
    private FieldController fieldAController;
    @FXML
    private FieldController fieldBController;
    @FXML
    private HandController handAController;
    @FXML
    private HandController handBController;

    public BoardController() {

    }

    @FXML
    public void initialize() {
        handAController.setPlayerType(PlayerType.A);
        handBController.setPlayerType(PlayerType.B);
        handAController.render();
        handBController.render();
        handAController.setCorrespondingField(fieldAController);
        handBController.setCorrespondingField(fieldBController);

        fieldBController.swapCharactersAndSkillsPosition();
        fieldAController.setPlayerType(PlayerType.A);
        fieldBController.setPlayerType(PlayerType.B);
        fieldAController.setEnemyFieldController(fieldBController);
        fieldBController.setEnemyFieldController(fieldAController);
    }

    @FXML
    private void setData(CharacterCard card) {
        selectedController.setCard(card);
    }

    public void setSelectedCard(CharacterCard card) {
        setData(card);
    }
}
