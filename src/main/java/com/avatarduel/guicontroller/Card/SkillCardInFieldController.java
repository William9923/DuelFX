package com.avatarduel.guicontroller.Card;

import com.avatarduel.guicontroller.Board.FieldController;
import com.avatarduel.guicontroller.Request.FieldRenderRequest;
import com.avatarduel.guicontroller.Request.ShowSelectedCardRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCard;
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
    public void removeCard() {
        System.out.println("SkillCardInFieldController : removing card... ");
        Game.getInstance().getPlayerByType(this.playerType).getField().removeSkillCard((SkillCard) skillCardInField.getCard());
        Game.getInstance().getEventBus().post(new FieldRenderRequest(PlayerType.A));
        Game.getInstance().getEventBus().post(new FieldRenderRequest(PlayerType.B));
        System.out.println("SkillCardInFieldController : remove successfull");
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
