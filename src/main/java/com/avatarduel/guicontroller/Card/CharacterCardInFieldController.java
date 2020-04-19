package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.ChangePositionEvent;
import com.avatarduel.event.DirectAttackEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.event.NextPhaseEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Popup.AttackPopupLoader;
import com.avatarduel.guicontroller.Request.GlobalRequest.GameStatusRenderRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.PlayerStatusRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

import java.util.List;

/**
 * used to manage data of character card in field, and manage its action such as rotating and attacking
 */
public class CharacterCardInFieldController extends CardInFieldController {
    @FXML ImageView card_rotate;
    @FXML ImageView card_attack;
    private CharacterCardInField characterCardInField;

    /**
     * set the text, image, etc of the card in field
     */
    public void setCard(CharacterCardInField cardInField) {
        super.setCard(cardInField.getCard());
        this.characterCardInField = cardInField;
        super.card_atk.setText("ATK : " + characterCardInField.getTotalAttack());
        super.card_def.setText("DEF : " +  characterCardInField.getTotalDefense());
    }

    /**
     * rotate the card
     */
    @FXML
    public void rotateCard() {
        try {
            IEvent event = new ChangePositionEvent(playerType, characterCardInField.getCard().getId());
            event.execute();
            this.renderRotate();
        }
        catch (InvalidOperationException e) {
            Game.getInstance().getEventBus().post(e);
        }
    }

    /**
     * render the card rotation
     */
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

    /**
     * method to show attack popup loader, and posting exception render request
     */
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
    }

    /**
     * set player type
     * @param playerType the player type
     */
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
