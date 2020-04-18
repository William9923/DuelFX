package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.*;
import com.avatarduel.guicontroller.Popup.AttackPopupLoader;
import com.avatarduel.guicontroller.RenderRequest.GameStatusRenderRequest;
import com.avatarduel.guicontroller.RenderRequest.PlayerStatusRenderRequest;
import com.avatarduel.guicontroller.RenderRequest.CheckWinRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

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
        super.card_atk.setText("ATK : " + characterCardInField.getTotalAttack());
        super.card_def.setText("DEF : " +  characterCardInField.getTotalDefense());
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
            Game.getInstance().getEventBus().post(new GameStatusRenderRequest());
        }
        if (Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList().size() == 0){
            Game.getInstance().getEventBus().post(new DirectAttackEvent(this.getCardData().getId(), playerType));
            Game.getInstance().getEventBus().post(new PlayerStatusRenderRequest(Game.getInstance().getCurrentOpponent()));
        }
        else if (Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList().size() > 0) {
            List<CharacterCardInField> opponentField = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList();
            AttackPopupLoader attackPopupLoader = new AttackPopupLoader(this.characterCardInField, opponentField);
            Popup attackPopup = attackPopupLoader.getPopup();
            attackPopup.show(card_rotate.getScene().getWindow());
        }
        Game.getInstance().getEventBus().post(new CheckWinRequest());
        Game.getInstance().getEventBus().post(this.characterCardInField);
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
