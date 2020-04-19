package com.avatarduel.guicontroller.Board;

import com.avatarduel.event.EndTurnEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Card.DisplayCardController;
import com.avatarduel.guicontroller.Request.GlobalRequest.GameStatusRenderRequest;
import com.avatarduel.guicontroller.Request.GlobalRequest.ShowSelectedCardRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.CheckWinRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.DeckDrawAndRenderRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.PlayerStatusRenderRequest;
import com.avatarduel.guicontroller.util.PlayMusicRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;

/**
 * BoardController is the one big class that controls:
 * alert
 * playing song
 * ending turn
 */
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

    private Thread musicThread;
    private MediaPlayer mediaPlayer;

    public BoardController() {
    }

    @FXML
    public void initialize() {
        Game.getInstance().getEventBus().register(this);

        handAController.setPlayerTypeAndRender(PlayerType.A);
        handBController.setPlayerTypeAndRender(PlayerType.B);
        handBController.flipCards(); // game start, second player need to flip the card

        fieldAController.setPlayerType(PlayerType.A);
        fieldBController.setPlayerType(PlayerType.B);
        fieldBController.swapCharactersAndSkillsPosition();

        deckAController.setPlayerTypeAndRender(PlayerType.A);
        deckBController.setPlayerTypeAndRender(PlayerType.B);
        Game.getInstance().getEventBus().post(new DeckDrawAndRenderRequest(Game.getInstance().getCurrentPlayer()));

        playerAStatusController.setPlayerType(PlayerType.A);
        playerBStatusController.setPlayerType(PlayerType.B);
    }

    @FXML
    public void endTurn() {
        EndTurnEvent event = new EndTurnEvent();
        boolean canDoIt = event.validate();
        Game.getInstance().getEventBus().post(new EndTurnEvent());
        Game.getInstance().getEventBus().post(new GameStatusRenderRequest());
        Game.getInstance().getEventBus().post(new DeckDrawAndRenderRequest(Game.getInstance().getCurrentPlayer()));
        Game.getInstance().getEventBus().post(new PlayerStatusRenderRequest(Game.getInstance().getCurrentPlayer()));

        if (canDoIt) {
            handAController.flipCards();
            handBController.flipCards();
            PlayerType nextPlayer = Game.getInstance().getCurrentPlayer();
            if(nextPlayer == PlayerType.A) {
                handAController.render();
            }
            else {
                handBController.render();
            }
            fieldAController.setCharactersActionsVisible(Game.getInstance().getCurrentPlayer() == PlayerType.A);
            fieldBController.setCharactersActionsVisible(Game.getInstance().getCurrentPlayer() == PlayerType.B);
        }
    }

    @Subscribe
    public void catchException(InvalidOperationException exception) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(exception.getOperation());
        a.setContentText(exception.getMessage());
        a.show();
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
        if (Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getDeck().size() <= 0){
            a.setContentText("Player " + Game.getInstance().getCurrentOpponent() + "Win!!!");
            gameEnded = true;
        }
        if (Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getHealthPoint() <= 0) {
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
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
            }
            catch(Exception e) { }
        });
        musicThread.start();
    }
}
