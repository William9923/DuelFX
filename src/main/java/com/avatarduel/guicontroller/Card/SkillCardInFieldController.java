package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.IEvent;
import com.avatarduel.event.RemoveSkillCardEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Request.SpecificRequest.FieldRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;

/**
 * used to for controlling the skill card in field and manage its action such as
 * removing it from the field
 * @author G10-K03-CardGameOOP
 */
public class SkillCardInFieldController extends CardInFieldController {
    private SkillCardInField skillCardInField;

    /**
     * set this card skillcardinfield
     * @param cardInField this cardinfield
     */
    public void setCard(SkillCardInField cardInField) {
        super.setCard(cardInField.getCard());
        this.skillCardInField = cardInField;
    }

    /**
     * used to post remove the skill card from field, will trigger when the trash bin button clicked
     */
    @FXML
    public void removeCard(){
        try {
            IEvent removeSkillCardEvent = new RemoveSkillCardEvent(this.skillCardInField.getCard().getId(), playerType);
            removeSkillCardEvent.execute();
            Game.getInstance().getEventBus().post(new FieldRenderRequest(PlayerType.A));
            Game.getInstance().getEventBus().post(new FieldRenderRequest(PlayerType.B));
        }
        catch (InvalidOperationException e) {
            Game.getInstance().getEventBus().post(e);
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
