package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.RemoveSkillCardEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Board.FieldController;
import com.avatarduel.guicontroller.RenderRequest.FieldRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;

public class SkillCardInFieldController extends CardInFieldController {
    private SkillCardInField skillCardInField;
    private FieldController fieldController;

    @FXML
    public void initialize() {
        card_actions.setVisible(false);
    }

    public void setCard(SkillCardInField cardInField) {
        super.setCard(cardInField.getCard());
        this.skillCardInField = cardInField;
    }

    @FXML
    public void removeCard(){
        Game.getInstance().getEventBus().post(new RemoveSkillCardEvent(this.skillCardInField.getCard().getId(), playerType));
        Game.getInstance().getEventBus().post(new FieldRenderRequest(PlayerType.A));
        Game.getInstance().getEventBus().post(new FieldRenderRequest(PlayerType.B));
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
