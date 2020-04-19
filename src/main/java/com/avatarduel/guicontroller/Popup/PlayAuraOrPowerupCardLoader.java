package com.avatarduel.guicontroller.Popup;

import com.avatarduel.event.ActivateSkillEvent;
import com.avatarduel.event.IEvent;
import com.avatarduel.exception.ExceptionCause.NoCharacterCardInFieldCause;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.exception.InvalidSkillActivationException;
import com.avatarduel.guicontroller.Request.SpecificRequest.FieldRenderRequest;
import com.avatarduel.guicontroller.Request.SpecificRequest.HandRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CardInHand;
import com.avatarduel.model.card.CharacterCardInField;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Popup;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@inheritDoc}
 * used to show popup when player click play on
 * skill power up card or skill aura card. Because their behavior
 * is similar, that is can be attached to any card in field, the popup
 * is grouped into one.
 * @author G10-K03-CardGameOOP
 */
public class PlayAuraOrPowerupCardLoader extends PopupLoader {
    private CardInHand cardPlayed;
    private ChoiceBox<CharacterCardInField> choiceBox;

    /**
     * play the card, try to load all of the card on the field
     * if the field is new, throw an invalid skill activation
     * if not, laod all options to the choice box
     * @param cardPlayed the skill card played
     * @throws InvalidOperationException if list of character card is empty
     */
    public PlayAuraOrPowerupCardLoader(CardInHand cardPlayed) throws InvalidOperationException {
        super();
        this.choiceBox = (ChoiceBox<CharacterCardInField>) popupGui.lookup("#choice_box");
        List<CharacterCardInField> myCharactersInField = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getCharCardList();
        List<CharacterCardInField> opponentCharactersInField = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList();
        List<CharacterCardInField> listOfCharacterCards = Stream.of(myCharactersInField, opponentCharactersInField)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        if (listOfCharacterCards.isEmpty()) {
            throw new InvalidSkillActivationException(new NoCharacterCardInFieldCause(cardPlayed.getCard().getType()));
        }
        this.title.setText("Select Character to Use " + StringUtils.capitalize(cardPlayed.getCard().getType().toString()) + " Card");
        this.cardPlayed = cardPlayed;
        this.choiceBox.setItems(new ObservableListWrapper<>(listOfCharacterCards));
    }

    /**
     * set up the confirm button, and set up the popup
     * if there is an invalid operation in the execution of the event,
     * post the exception to eventbus to get handled by board controller
     * {@inheritDoc
     * @author G10-K03-CardGameOOP
     */
    @Override
    public Popup getPopup() {
        Popup popup = new Popup();
        popup.getContent().add(this.popupGui);

        this.confirmButton.setOnMouseClicked(event -> {
            if(choiceBox.getSelectionModel().getSelectedItem() == null) {
                popup.hide();
                return;
            }
            IEvent skillEvent = new ActivateSkillEvent(cardPlayed.getCard().getId(), choiceBox.getSelectionModel().getSelectedItem().getCard().getId(), cardPlayed.getPlayerType());
            try {
                skillEvent.execute();
                Game.getInstance().getEventBus().post(new HandRenderRequest(cardPlayed.getPlayerType()));  // render tangan lagi soalny kartunya uda dipake
                Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentPlayer()));
                Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentOpponent()));
            }
            catch (InvalidOperationException e) {
                Game.getInstance().getEventBus().post(e);
            }
            popup.hide();
        });
        this.cancelButton.setOnAction(e -> {
            popup.hide();
        });
        return popup;
    }
}
