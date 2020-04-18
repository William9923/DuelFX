package com.avatarduel.guicontroller.Popup;

import com.avatarduel.event.ActivateDestroyEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.exception.EmptyFieldException;
import com.avatarduel.exception.ExceptionCause.NoCharacterCardInFieldCause;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.RenderRequest.FieldRenderRequest;
import com.avatarduel.guicontroller.RenderRequest.HandRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CardInHand;
import com.avatarduel.model.card.CharacterCardInField;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlayDestroyCardLoader extends PopupLoader {
    private CardInHand cardPlayed;
    private ChoiceBox<CharacterCardInField> choiceBox;

    public PlayDestroyCardLoader(CardInHand cardPlayed) {
        super();
        this.choiceBox = (ChoiceBox<CharacterCardInField>) popupGui.lookup("#choice_box");
        try {
            List<CharacterCardInField> opponentCharactersInField = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList();
            if (opponentCharactersInField.isEmpty()) {
                throw new EmptyFieldException(new NoCharacterCardInFieldCause(cardPlayed.getCard().getType()));
            }
            this.title.setText("Select Character to Use " + StringUtils.capitalize(cardPlayed.getCard().getType().toString()) + " Card");
            this.cardPlayed = cardPlayed;
            this.choiceBox.setItems(new ObservableListWrapper<>(opponentCharactersInField));
        }
        catch ( InvalidOperationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getOperation());
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public Popup getPopup() {
        Popup popup = new Popup();
        popup.getContent().add(this.popupGui);

        this.confirmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            IEvent skillEvent;
            @Override
            public void handle(MouseEvent event) {
                if(choiceBox.getSelectionModel().getSelectedItem() == null) {
                    popup.hide();
                    return;
                }
                skillEvent = new ActivateDestroyEvent(cardPlayed.getPlayerType(), cardPlayed.getCard().getId(), choiceBox.getSelectionModel().getSelectedItem().getCard().getId());
                try {
                    skillEvent.execute();
                    Game.getInstance().getEventBus().post(new HandRenderRequest(cardPlayed.getPlayerType()));  // render tangan lagi soalny kartunya uda dipake
                    Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentPlayer()));
                    Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentOpponent()));
                }
                catch (InvalidOperationException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getOperation());
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
                popup.hide();
            }
        });
        this.cancelButton.setOnAction(e -> {
            popup.hide();
        });
        return popup;
    }
}
