package com.avatarduel.guicontroller.Popup;

import com.avatarduel.event.AttackEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Request.SpecificRequest.CheckWinRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.FieldRenderRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.PlayerStatusRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Popup;

import java.util.List;

public class AttackPopupLoader extends PopupLoader {
    private CharacterCardInField attacker;
    private ChoiceBox<CharacterCardInField> choiceBox;

    public AttackPopupLoader(CharacterCardInField attacker) {
        super();
        try {
            this.attacker = attacker;
            popupGui = fxmlHandler.getParent();
            List<CharacterCardInField> opponentField = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList();
            choiceBox = (ChoiceBox<CharacterCardInField>) popupGui.lookup("#choice_box");
            choiceBox.setItems(new ObservableListWrapper<>(opponentField));
        }
        catch(Exception e) {
            System.out.println("AttackPopupLoader.java : constructor : " + e.getMessage());
        }
    }

    public Popup getPopup() {
        Popup popup = new Popup();
        popup.getContent().add(popupGui);
        Button attack = (Button) popupGui.lookup("#confirm_button");
        attack.setOnAction(event -> {
            try {
                if (choiceBox.getSelectionModel().getSelectedItem() != null) {
                    IEvent attackEvent = new AttackEvent(attacker.getCard().getId(), choiceBox.getSelectionModel().getSelectedItem().getCard().getId(), Game.getInstance().getCurrentPlayer(), Game.getInstance().getCurrentOpponent());
                    attackEvent.execute();
                    Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentOpponent()));
                    Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentPlayer()));
                    Game.getInstance().getEventBus().post(new PlayerStatusRenderRequest(Game.getInstance().getCurrentOpponent()));
                    Game.getInstance().getEventBus().post(new CheckWinRequest());
                }
                popup.hide();
            }
            catch (InvalidOperationException e) {
                Game.getInstance().getEventBus().post(e);
            }
        });
        Button cancel = (Button) popupGui.lookup("#cancel_button");
        cancel.setOnAction(event -> popup.hide());
        return popup;
    }
}
