package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Card.DisplayCardController;
import com.avatarduel.guicontroller.Request.ShowSelectedCardRequest;
import com.avatarduel.guicontroller.Server.Channel;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.Map;

public class BoardController {
    @FXML private DisplayCardController selectedController;
    @FXML private FieldController fieldAController;
    @FXML private FieldController fieldBController;
    @FXML private HandController handAController;
    @FXML private HandController handBController;
    @FXML private DeckController deckAController;
    @FXML private DeckController deckBController;
    @FXML private PlayerStatusController playerAStatusController;
    @FXML private PlayerStatusController playerBStatusController;
    @FXML private Button end_turn;
    @FXML private Button end_phase;
    @FXML private GameStatusController gameStatusController;

    private Map<PlayerType, HandController> handControllerMap;
    private Map<PlayerType, FieldController> fieldControllerMap;
    private Map<PlayerType, PlayerStatusController> playerStatusControllerMap;
    private Map<PlayerType, DeckController> deckControllerMap;

    public BoardController() {
        handControllerMap = new HashMap<>();
        fieldControllerMap = new HashMap<>();
        playerStatusControllerMap = new HashMap<>();
        deckControllerMap = new HashMap<>();
        Game.getInstance().getEventBus().register(this);
    }

    @FXML
    public void initialize() {
        // hand mapping setup
        handControllerMap.put(PlayerType.A, handAController);
        handControllerMap.put(PlayerType.B, handBController);

        handControllerMap.forEach((playerType ,controller) -> {
            controller.setPlayerTypeAndRender(playerType);
            Game.getInstance().getEventBus().register(controller);
        });

        handBController.flipCards(); // game start, second player need to flip the card

        // field mapping setup
        fieldControllerMap.put(PlayerType.A, fieldAController);
        fieldControllerMap.put(PlayerType.B, fieldBController);

        fieldControllerMap.forEach((playerType, fieldController) -> {
            fieldController.setPlayerType(playerType);
            Game.getInstance().getEventBus().register(fieldController);
        });
        fieldBController.swapCharactersAndSkillsPosition();

        deckControllerMap.put(PlayerType.A, deckAController);
        deckControllerMap.put(PlayerType.B, deckBController);
        deckControllerMap.forEach((playerType, deckController) -> {
            deckController.setPlayerType(playerType);
        });

        playerStatusControllerMap.put(PlayerType.A, playerAStatusController);
        playerStatusControllerMap.put(PlayerType.B, playerBStatusController);
        playerStatusControllerMap.forEach((playerType, playerStatusController) -> {
            playerStatusController.setPlayerType(playerType);
        });

        // Initialize Game Server
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.DECK, deckAController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.DECK, deckBController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.HAND, handAController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.HAND, handBController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.FIELD, fieldAController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.FIELD, fieldBController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.PLAYER_A, handAController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.PLAYER_A, fieldAController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.PLAYER_A, playerAStatusController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.PLAYER_B, handBController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.PLAYER_B, fieldBController);
        Game.getInstance().getGUIRenderServer().addSubscriber(Channel.PLAYER_B, playerBStatusController);

        Game.getInstance().getGUIRenderServer().renderAll(Channel.DECK);
    }

    @FXML
    public void endTurn() {
        Game.getInstance().endTurn();
//        Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).draw();
        PlayerType nextPlayer = Game.getInstance().getCurrentPlayer();
        handControllerMap.get(nextPlayer).render();
        handAController.flipCards();
        handBController.flipCards();
        deckControllerMap.get(nextPlayer).render();
        gameStatusController.render();
        fieldControllerMap.forEach((playerType, controller) -> {
            controller.setCharactersActionsVisible(Game.getInstance().getCurrentPlayer() == playerType);
        });
    }

    @FXML
    public void endPhase() {
        Game.getInstance().nextPhase();
        gameStatusController.render();
    }

    // TODO : IMPLEMENT CARD HOVER ON CARD CONTROLLER TO POST AN EVENT
    // Buat Card jadi HoveredCard
    @Subscribe
    private void showSelectCard(ShowSelectedCardRequest selectCardRequest) {
        selectedController.setCard(selectCardRequest.getCard());
    }
}
