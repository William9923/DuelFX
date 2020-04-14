package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.*;
import com.avatarduel.guicontroller.Request.CheckWinRequest;
import com.avatarduel.guicontroller.Request.FieldRenderRequest;
import com.avatarduel.guicontroller.Request.RenderRequest;
import com.avatarduel.guicontroller.Request.ShowSelectedCardRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.List;

public class CharacterCardInFieldController extends CardInFieldController {
    @FXML ImageView card_rotate;
    @FXML ImageView card_attack;
    private CharacterCardInField characterCardInField;

    @FXML
    public void initialize() {
        card_actions.setVisible(false);
    }

    public void setCard(CharacterCardInField cardInField) {
        super.setCard(cardInField.getCard());
        this.characterCardInField = cardInField;
    }

    @FXML
    public void rotateCard() {
        IEvent event = new ChangePositionEvent(playerType, characterCardInField.getCard().getId());
        Game.getInstance().getEventBus().post(event);
        this.renderRotate();
    }

    public void renderRotate() {
        if(characterCardInField.getPosition() == CharacterState.DEFENSE) {
            card_border.rotateProperty().setValue(90);
            card_attack.setVisible(false);
        }
        else{
            card_border.rotateProperty().setValue(0);
            card_attack.setVisible(true);
        }
    }

    @FXML
    public void cardAttack() {
        if(Game.getInstance().getCurrentPhase().getPhase() != Phase.BATTLE) {
            Game.getInstance().getEventBus().post(new NextPhaseEvent());
            Game.getInstance().getEventBus().post(new RenderRequest());
        }
        if (Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList().size() == 0){
            Game.getInstance().getEventBus().post(new DirectAttackEvent(this.getCardData().getId(), playerType));
            Game.getInstance().getEventBus().post(new RenderRequest());
        }
        else if (Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList().size() > 0) {
            List<CharacterCardInField> opponentField = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList();
            ChoiceDialog<CharacterCardInField> choiceAttack = new ChoiceDialog<>(opponentField.get(0), opponentField);
            choiceAttack.showAndWait();
            if (choiceAttack.getSelectedItem() != null) {
                IEvent event = new AttackEvent(this.getCardData().getId(), choiceAttack.getSelectedItem().getCard().getId(), Game.getInstance().getCurrentPlayer(), Game.getInstance().getCurrentOpponent());
                Game.getInstance().getEventBus().post(event);
                Game.getInstance().getEventBus().post(new RenderRequest());
                Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentOpponent()));
                Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentPlayer()));
            }
        }
        Game.getInstance().getEventBus().post(new CheckWinRequest());
        Game.getInstance().getEventBus().post(this.characterCardInField);
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
