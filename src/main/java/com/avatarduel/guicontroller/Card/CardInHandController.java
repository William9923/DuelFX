package com.avatarduel.guicontroller.Card;

import com.avatarduel.event.*;
import com.avatarduel.guicontroller.RenderRequest.*;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.*;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
            event = new SummonEvent(cardData.getId(), Game.getInstance().getCurrentPlayer(), CharacterState.ATTACK, getSmallestCharacterIndexPossible(Game.getInstance().getCurrentPlayer()));
            Game.getInstance().getEventBus().post(event);
            Game.getInstance().getEventBus().post(new HandRenderRequest(playerType));
            Game.getInstance().getEventBus().post(new FieldRenderRequest(playerType));
        }
        else if (cardData.getType() == CardType.SKILL_DESTROY) {
            if (Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList().size() > 0) {
                List<CharacterCardInField> listOfCard = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList();
                ChoiceDialog<CharacterCardInField> choice = new ChoiceDialog<>(listOfCard.get(0),listOfCard);
                choice.setHeaderText("Destroy Card Effect");
                choice.setContentText("Select Character To Destroy :");
                Optional<CharacterCardInField> result = choice.showAndWait();
                if (choice != null && result.isPresent()) {
                    event = new ActivateDestroyEvent(playerType, cardData.getId(), choice.getSelectedItem().getCard().getId());
                    Game.getInstance().getEventBus().post(event);
                    Game.getInstance().getEventBus().post(new PlayerStatusRenderRequest(playerType));
                    Game.getInstance().getEventBus().post(new GameStatusRenderRequest());
                    Game.getInstance().getEventBus().post(new HandRenderRequest(playerType)); // render tangan lagi soalny uda dipake kartuny
                    Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentOpponent())); // render opponent field
                }
            } else { // kalo ga ada karakter yang bisa dihancurin
                Alert alert = this.createInfoAlert("Destroy Card Effect","No Card To Destroy");
                alert.showAndWait();
            }
        } else {
            // kartu skill aura or power up
            int size1 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getCharCardList().size();
            int size2 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList().size();
            if ((size1 + size2)> 0) {
                List<CharacterCardInField> listOfCard1 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getCharCardList();
                List<CharacterCardInField> listOfCard2 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getCharCardList();
                List<CharacterCardInField> listOfCard = Stream.of(listOfCard1, listOfCard2)
                        .flatMap(x -> x.stream())
                        .collect(Collectors.toList());
                ChoiceDialog<CharacterCardInField> choice = new ChoiceDialog<>(listOfCard.get(0),listOfCard);
                choice.setHeaderText("Skill Equip Card Effect");
                choice.setContentText("Select Character To Equip :");
                Optional<CharacterCardInField> result = choice.showAndWait();
                if (choice != null && result.isPresent()) {
                    event = new ActivateSkillEvent(cardData.getId(),choice.getSelectedItem().getCard().getId(),playerType);
                    Game.getInstance().getEventBus().post(event); // post eventnya
                    Game.getInstance().getEventBus().post(new PlayerStatusRenderRequest(playerType)); // minta render terkait status player dan status game
                    Game.getInstance().getEventBus().post(new GameStatusRenderRequest());
                    Game.getInstance().getEventBus().post(new HandRenderRequest(playerType));  // render tangan lagi soalny kartunya uda dipake
                    Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentPlayer())); // minta render field sendiri
                    Game.getInstance().getEventBus().post(new FieldRenderRequest(Game.getInstance().getCurrentOpponent()));
                }
            } else {
                Alert alert = this.createInfoAlert("Skill Card Effect","No Card To Equip");
                alert.showAndWait();
            }
        }
        Game.getInstance().getEventBus().post(new PlayerStatusRenderRequest(playerType));
    }

    private int getSmallestCharacterIndexPossible(PlayerType type) {
        List<CharacterCardInField> listOfCharacter = Game.getInstance().getPlayerByType(type).getField().getCharCardList();
        List<Integer> indexList = listOfCharacter.stream()
                .map(c -> c.getIndex())
                .collect(Collectors.toList());
        return getSmallestIndex(indexList);
    }

    private int getSmallestIndex(List<Integer> sequence) {
        for (int i = 0; i < sequence.size() + 1; i++){
            if (!sequence.contains(i)){
                return i;
            }
        }
        return -1;
    }

    private int getSmallestSkillIndexPossible(PlayerType type) {
        List<SkillCardInField> listOfSkill = Game.getInstance().getPlayerByType(type).getField().getSkillCardList();
        List<Integer> indexList = listOfSkill.stream()
                .map(c -> c.getIndex())
                .collect(Collectors.toList());
        return getSmallestIndex(indexList);
    }

    private Alert createInfoAlert(String header, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(text);
        return alert;
    }

    public void showSelectedCard() {
        if(cardData != null && this.playerType == Game.getInstance().getCurrentPlayer()) {
            Game.getInstance().getEventBus().post(new ShowSelectedCardRequest(this.cardData));
        }
    }
}