package com.avatarduel.guicontroller.Popup;

import com.avatarduel.event.AttackEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.guicontroller.RenderRequest.FieldRenderRequest;
import com.avatarduel.guicontroller.RenderRequest.PlayerStatusRenderRequest;
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

    public AttackPopupLoader(CharacterCardInField attacker, List<CharacterCardInField> characterCardInFieldList) {
        super();
        try {
            this.attacker = attacker;
            popupGui = fxmlHandler.getParent();
            choiceBox = (ChoiceBox<CharacterCardInField>) popupGui.lookup("#choice_box");
            choiceBox.setItems(new ObservableListWrapper<>(characterCardInFieldList));
        }
        catch(Exception e) {
            System.out.println("AttackPopupLoader.java : constructor : " + e.getMessage());
        }
    }

    public Popup getPopup() {
        Popup popup = new Popup();
        popup.getContent().add(popupGui);
        Button attack = (Button) popupGui.lookup("#confirm_button");
        attack.setOnAction(e -> {
            if (choiceBox.getSelectionModel().getSelectedItem() != null) {
                IEvent event = new AttackEvent(attacker.getCard().getId(), choiceBox.getSelectionModel().getSelectedItem().getCard().getId(), Game.getInstance().getCurrentPlayer(), Game.getInstance().getCurrentOpponent());
                Game.getInstance().getEventBus().post(event);
                Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentOpponent()));
                Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentPlayer()));
            }
            popup.hide();
        });
        Button cancel = (Button) popupGui.lookup("#cancel_button");
        cancel.setOnAction(event -> popup.hide());
        return popup;
    }
}
