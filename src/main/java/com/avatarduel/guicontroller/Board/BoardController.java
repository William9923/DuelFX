package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Card.DisplayCardController;
import com.avatarduel.guicontroller.Server.Channel;
import com.avatarduel.guicontroller.Server.GameServer;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Map;

public class BoardController {
    @FXML private DisplayCardController selectedController;
    @FXML private FieldController fieldAController;
    @FXML private FieldController fieldBController;
    @FXML private HandController handAController;
    @FXML private HandController handBController;
    @FXML private DeckController deckAController;
    @FXML private DeckController deckBController;
    @FXML private Button end_turn;
    @FXML private Button end_phase;
    private GameServer gameServer;

    public BoardController() {

    }

    @FXML
    public void initialize() {
        handAController.setPlayerType(PlayerType.A);
        handBController.setPlayerType(PlayerType.B);
        handAController.render();
        handBController.render();
        handBController.flipCards();
        handAController.setCorrespondingField(fieldAController);
        handBController.setCorrespondingField(fieldBController);

        fieldBController.swapCharactersAndSkillsPosition();
        fieldAController.setPlayerType(PlayerType.A);
        fieldBController.setPlayerType(PlayerType.B);
        fieldAController.setEnemyFieldController(fieldBController);
        fieldBController.setEnemyFieldController(fieldAController);

        deckAController.setPlayerType(PlayerType.A);
        deckBController.setPlayerType(PlayerType.B);

        gameServer = new GameServer();
        gameServer.addSubscriber(Channel.DECK, deckAController);
        gameServer.addSubscriber(Channel.DECK, deckBController);
        gameServer.addSubscriber(Channel.HAND, handAController);
        gameServer.addSubscriber(Channel.HAND, handBController);

        gameServer.renderAll(Channel.DECK);
    }

    @FXML
    public void endTurn() {
        Game.getInstance().nextPlayer();
        gameServer.renderAll(Channel.HAND);
        handAController.flipCards();
        handBController.flipCards();
        gameServer.renderAll(Channel.DECK);
    }

    @FXML
    public void endPhase() {
        Game.getInstance().nextPhase();
    }

    @FXML
    private void setData(CharacterCard card) {
        selectedController.setCard(card);
    }

    public void setSelectedCard(CharacterCard card) {
        setData(card);
    }
}
