package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.IEvent;
import com.avatarduel.event.PlayLandCardEvent;
import com.avatarduel.event.SummonEvent;
import com.avatarduel.exception.ExceptionCause.InvalidPhaseCause;
import com.avatarduel.exception.ExceptionCause.NotEnoughPowerCause;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.exception.InvalidPlayCardException;
import com.avatarduel.guicontroller.Popup.PlayAuraOrPowerupCardLoader;
import com.avatarduel.guicontroller.Popup.PlayDestroyCardLoader;
import com.avatarduel.guicontroller.Request.GlobalRequest.ShowSelectedCardRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.FieldRenderRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.HandRenderRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.PlayerStatusRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CardInHand;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Popup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * used to set the card in hand and playing it
 * @author G10-K03-CardGameOOP
 */
public class CardInHandController extends CardController{
    @FXML private Button card_play;
    private String borderStyle;
    private PlayerType playerType;
    private CardInHand cardInHand;

    /**
     * initizlizing the card in hand, set the button to no visible and borderstyle to null
     */
    @FXML
    public void initialize() {
        card_play.setVisible(false);
        borderStyle = "null_card";
    }

    /**
     * {@inheritDoc}
     * set the card and the borderstyle
     * @param card for calling parent's setcard and setting new cardInhand for this object
     */
    @Override
    public void setCard(Card card) {
        super.setCard(card);
        borderStyle = card_border.getStyleClass().get(0);
        this.cardInHand = new CardInHand(card, playerType);
    }

    /**
     * {@inheritDoc}
     * set null card and set this.cardinhand to null
     */
    @Override
    public void setNullCard() {
        super.setNullCard();
        this.cardInHand = null;
    }

    /**
     * flip the cards, hiding the information
     * if it's on closed position, then open it
     */
    public void flipCard() {
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

    /**
     * set player type
     * @param playerType the player type
     */
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    /**
     * show the play button if it's the player turn and this cardInHand is not null
     */
    @FXML
    public void showPlayButton() {
        if (playerType == Game.getInstance().getCurrentPlayer() && cardInHand != null) {
            card_play.setVisible(true);
        }
    }

    /**
     * hide the play button
     */
    @FXML
    public void hidePlayButton() {
        card_play.setVisible(false);
    }

    /**
     * method for creating playing card event , executing it
     * if execution failed, post the exception to eventbus so the boardController can display the alert message
     */
    @FXML
    public void playIsClicked() {
        if(Game.getInstance().getCurrentPhase().getPhase() != Phase.MAIN) {
            Game.getInstance().getEventBus().post(new InvalidPlayCardException(new InvalidPhaseCause(Phase.MAIN)));
            return;
        }

        IEvent event;
        try {
            if (cardData.getType() == CardType.LAND) {
                event = new PlayLandCardEvent(cardData.getId(), playerType);
                event.execute();
                Game.getInstance().getEventBus().post(new HandRenderRequest(playerType));
            }
            else if(Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getPower().getCurrent(this.cardData.getElement()) < this.cardData.getPower()) {
                Game.getInstance().getEventBus().post(new InvalidPlayCardException(new NotEnoughPowerCause(this.cardData.getElement())));
                return;
            }
            else if (cardData.getType() == CardType.CHARACTER) {
                event = new SummonEvent(cardData.getId(), Game.getInstance().getCurrentPlayer(), CharacterState.ATTACK, getSmallestCharacterIndexPossible());
                event.execute();
                Game.getInstance().getEventBus().post(new HandRenderRequest(playerType));
                Game.getInstance().getEventBus().post(new FieldRenderRequest(playerType));
            } else if (cardData.getType() == CardType.SKILL_DESTROY) {
                PlayDestroyCardLoader playDestroyCardLoader = new PlayDestroyCardLoader(this.cardInHand);
                Popup popup = playDestroyCardLoader.getPopup();
                popup.show(card_play.getScene().getWindow());
            } else {
                // kartu skill aura or power up
                PlayAuraOrPowerupCardLoader playAuraOrPowerupCardLoader = new PlayAuraOrPowerupCardLoader(this.cardInHand);
                Popup popup = playAuraOrPowerupCardLoader.getPopup();
                popup.show(card_play.getScene().getWindow());
            }
            Game.getInstance().getEventBus().post(new PlayerStatusRenderRequest(playerType));
        }
        catch (InvalidOperationException IOE) {
            Game.getInstance().getEventBus().post(IOE);
        }
    }

    /**
     * get the smallest character index to display on field
     */
    private int getSmallestCharacterIndexPossible() {
        List<CharacterCardInField> listOfCharacter = Game.getInstance().getPlayerByType(playerType).getField().getCharCardList();
        List<Integer> indexList = listOfCharacter.stream()
                .map(c -> c.getIndex())
                .collect(Collectors.toList());
        return getSmallestIndex(indexList);
    }

    /**
     * get smallest index from a sequence of integer
     */
    private int getSmallestIndex(List<Integer> sequence) {
        for (int i = 0; i < sequence.size() + 1; i++){
            if (!sequence.contains(i)){
                return i;
            }
        }
        return -1;
    }

    /**
     * method for posting the selected card to the boardcontroller for display
     */
    public void showSelectedCard() {
        if(cardData != null && this.playerType == Game.getInstance().getCurrentPlayer()) {
            Game.getInstance().getEventBus().post(new ShowSelectedCardRequest(this.cardData));
        }
    }
}