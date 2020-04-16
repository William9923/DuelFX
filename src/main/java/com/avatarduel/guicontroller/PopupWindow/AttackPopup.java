package com.avatarduel.guicontroller.PopupWindow;

import com.avatarduel.event.AttackEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.event.NextPhaseEvent;
import com.avatarduel.guicontroller.RenderRequest.FieldRenderRequest;
import com.avatarduel.guicontroller.RenderRequest.GameStatusRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.Phase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class AttackPopup extends Popup {
    private CharacterCardInField attacker;
    private Parent gui;

    public AttackPopup(CharacterCardInField attackingCharacter) throws MalformedURLException, IOException {
        File guiFile = new File("src/main/resources/com/avatarduel/GUI/Popup/AttackPopup.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(guiFile.toURI().toURL());
        this.gui = fxmlLoader.load();
        System.out.println("is gui null ? : " + gui);
        this.attacker = attackingCharacter;
        List<CharacterCardInField> opponentCharacters = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList();
    }

    @Override
    public void show(Window window) {
        try {
//        ChoiceBox<CharacterCardInField> choiceBox = (ChoiceBox) gui.getScene().lookup("#choice_box");
            Button attackButton = (Button) gui.getScene().lookup("#attack_button");
            Button cancelButton = (Button) gui.getScene().lookup("#cancel_button");

            this.getContent().add(gui);

            attackButton.setOnAction(eventClicked -> {
//            IEvent event = new AttackEvent(this.attacker.getCard().getId(), choiceBox.getValue().getCard().getId(), Game.getInstance().getCurrentPlayer(), Game.getInstance().getCurrentOpponent());
//            if(Game.getInstance().getCurrentPhase().getPhase() != Phase.BATTLE) {
//                Game.getInstance().getEventBus().post(new NextPhaseEvent());
//                Game.getInstance().getEventBus().post(new GameStatusRenderRequest());
//            }
//            Game.getInstance().getEventBus().post(event);
//            Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentOpponent()));
//            Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentPlayer()));
                this.hide();
            });

            cancelButton.setOnAction(eventClicked -> {
                this.hide();
            });
            super.show(window);
        }
        catch (Exception e) {

        }
    }
}
