package com.avatarduel.guicontroller.Board;

import com.avatarduel.event.EndTurnEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Card.DisplayCardController;
import com.avatarduel.guicontroller.RenderRequest.*;
import com.avatarduel.guicontroller.util.PlayMusicRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
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
    @FXML private GameStatusController gameStatusController;
//    private Executor executor;

    private Map<PlayerType, HandController> handControllerMap;
    private Map<PlayerType, FieldController> fieldControllerMap;
    private Map<PlayerType, PlayerStatusController> playerStatusControllerMap;
    private Map<PlayerType, DeckController> deckControllerMap;

    private Thread musicThread;
    private MediaPlayer mediaPlayer;

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

        Game.getInstance().getEventBus().register(gameStatusController);
        fieldBController.swapCharactersAndSkillsPosition();

        deckControllerMap.put(PlayerType.A, deckAController);
        deckControllerMap.put(PlayerType.B, deckBController);
        deckControllerMap.forEach((playerType, deckController) -> {
            deckController.setPlayerType(playerType);
            deckController.render();
            Game.getInstance().getEventBus().register(deckController);
        });
        Game.getInstance().getEventBus().post(new DeckDrawAndRenderRequest(Game.getInstance().getCurrentPlayer()));

        playerStatusControllerMap.put(PlayerType.A, playerAStatusController);
        playerStatusControllerMap.put(PlayerType.B, playerBStatusController);
        playerStatusControllerMap.forEach((playerType, playerStatusController) -> {
            playerStatusController.setPlayerType(playerType);
            Game.getInstance().getEventBus().register(playerStatusController);
        });
    }

    @FXML
    public void endTurn() {
        EndTurnEvent event = new EndTurnEvent();
        boolean canDoIt = event.validate();
        Game.getInstance().getEventBus().post(new EndTurnEvent());
        Game.getInstance().getEventBus().post(new GameStatusRenderRequest());
        Game.getInstance().getEventBus().post(new DeckDrawAndRenderRequest(Game.getInstance().getCurrentPlayer()));
        Game.getInstance().getEventBus().post(new PlayerStatusRenderRequest(Game.getInstance().getCurrentPlayer()));

        if (canDoIt){
            // gw bikin kek gini karena blom bisa request Flip Card dkk gitu
            handAController.flipCards();
            handBController.flipCards();
            PlayerType nextPlayer = Game.getInstance().getCurrentPlayer();
            handControllerMap.get(nextPlayer).render();
            // dis kode di bawah ini so smart actually KWKW
            fieldControllerMap.forEach((playerType, controller) -> {
                controller.setCharactersActionsVisible(Game.getInstance().getCurrentPlayer() == playerType);
            });
        }
    }

    @Subscribe
    public void executeEvent(IEvent event) {
        try{
            event.execute();
        } catch (InvalidOperationException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(e.getOperation());
            a.setContentText(e.getMessage());
            a.show();
        }
    }

    @Subscribe
    public void checkWinnerGame(CheckWinRequest request) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        boolean gameEnded = false;
        a.setHeaderText("Congratulation");
        if (Game.getInstance().getCurrentPhase().getPhase().equals(Phase.DRAW) && Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getDeck().size() <= 0){
            a.setContentText("Player " + Game.getInstance().getCurrentOpponent() + "Win!!!");
            gameEnded = true;
        }
        if (Game.getInstance().getCurrentPhase().getPhase().equals(Phase.BATTLE) && Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getHealthPoint() <= 0) {
            a.setContentText("Player " + Game.getInstance().getCurrentPlayer() + "Win!!!");
            gameEnded = true;
        }
        if(gameEnded) {
            a.showAndWait();
            end_turn.getScene().getWindow().hide();
        }
    }

    @Subscribe
    private void showSelectCard(ShowSelectedCardRequest selectCardRequest) {
        selectedController.setCard(selectCardRequest.getCard());
    }

    @Subscribe
    public void playOnGameSong(PlayMusicRequest playMusicRequest) {
        musicThread = new Thread(() -> {
            try {
                File musicFile = new File("src/main/resources/com/avatarduel/music/on_game_song.mp3");
                URL musicURL = musicFile.toURI().toURL();
                Media media = new Media(musicURL.toString());
                this.mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(0.1);
                mediaPlayer.play();
            }
            catch(Exception e) { }
        });
        musicThread.start();
    }
}
