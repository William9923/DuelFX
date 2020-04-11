package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.IEvent;
import com.avatarduel.event.PlayLandCardEvent;
import com.avatarduel.event.SummonEvent;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.guicontroller.Board.FieldController;
import com.avatarduel.guicontroller.Board.HandController;
import com.avatarduel.guicontroller.Board.PlayerStatusController;
import com.avatarduel.guicontroller.Request.FieldRenderRequest;
import com.avatarduel.guicontroller.Request.HandRenderRequest;
import com.avatarduel.guicontroller.Request.ShowSelectedCardRequest;
import com.avatarduel.guicontroller.Server.Channel;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class CardInHandController extends CardController{
    @FXML private Button card_play;
    private String borderStyle;
    private PlayerType playerType;

    @FXML
    public void initialize() {
        card_play.setVisible(false);
        borderStyle = "null_card";
    }

    @Override
    public void setCard(Card card) {
        super.setCard(card);
        borderStyle = card_border.getStyleClass().get(0);
    }

    public void flipCard() {
        // kalo udah di flip, buka
        boolean isFlipped = Game.getInstance().getCurrentPlayer() == playerType;
        card_name.setVisible(isFlipped);
        card_icon.setVisible(isFlipped);
        card_img.setVisible(isFlipped);
        card_atk.setVisible(isFlipped);
        card_def.setVisible(isFlipped);
        card_pow.setVisible(isFlipped);
        super.removeAllBorderStyle();
        if(isFlipped) {
            super.addBorderStyle(borderStyle);
        }
        else {
            super.addBorderStyle("flipped_card");
        }
    }

    @FXML
    public void showPlayButton() {
        if (playerType == Game.getInstance().getCurrentPlayer()) {
            card_play.setVisible(true);
        }
    }

    @FXML
    public void hidePlayButton() {
        card_play.setVisible(false);
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    @FXML
    public void playIsClicked() {
        IEvent event;
        if(cardData.getType() == CardType.LAND) {
            event = new PlayLandCardEvent(cardData.getId(), playerType);
            Game.getInstance().getEventBus().post(event);
            Game.getInstance().getEventBus().post(new HandRenderRequest(playerType));
        }
        else if(cardData.getType() == CardType.CHARACTER) {
            CharacterState stateList[] = {CharacterState.ATTACK, CharacterState.DEFENSE};
            ChoiceDialog<CharacterState> state = new ChoiceDialog<>(stateList[0], stateList);
            state.setHeaderText("Pilihlah State Karakter : ");
            state.showAndWait();
            System.out.println(state.getSelectedItem());
            if (state.getSelectedItem() != null) {
                System.out.println(state.getSelectedItem());
                event = new SummonEvent(cardData.getId(), Game.getInstance().getCurrentPlayer(),state.getSelectedItem(), getSmallestIndexPossible(Game.getInstance().getCurrentPlayer()));
                Game.getInstance().getEventBus().post(event);
                Game.getInstance().getEventBus().post(new HandRenderRequest(playerType));
                Game.getInstance().getEventBus().post(new FieldRenderRequest(playerType));
            }
        }
        else if (cardData.getType() == CardType.SKILL_DESTROY) {
            if (Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList().size() > 0) {
                List<String> listOfCard = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getCharCardList()
                        .stream()
                        .map(c -> c.toString())
                        .collect(Collectors.toList());

                ChoiceDialog<String> dialog = new ChoiceDialog<>(listOfCard.get(0), listOfCard);
                dialog.setHeaderText("Pick Character to destroy");
                dialog.showAndWait();
                System.out.println();
                // implementasi blom
                // set wat to destroy
                // duar render apa ja yang perlu
                // mantap di gas lagi
        } else {
            // play skill aura card / play skill power up
            // buat sementara do nothing dl
            }
        }
    }

    private int getSmallestIndexPossible(PlayerType type) {
        List<CharacterCardInField> listOfCharacter = Game.getInstance().getPlayerByType(type).getField().getCharCardList();
        int max = -1;
        for (CharacterCardInField card : listOfCharacter) {
            max = (card.getIndex() > max) ? card.getIndex() : max;
        }
        return max + 1;
    }

    public void showSelectedCard() {
        if(cardData != null && this.playerType == Game.getInstance().getCurrentPlayer()) {
            Game.getInstance().getEventBus().post(new ShowSelectedCardRequest(this.cardData));
        }
    }
}