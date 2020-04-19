package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Card.CharacterCardInFieldController;
import com.avatarduel.guicontroller.Card.SkillCardInFieldController;
import com.avatarduel.guicontroller.Request.SpecificRequest.FieldRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controls setting cardds for each CardInField, swapping the character cards and skill card for player B
 * and setting characters action visible if it's the player's turn
 */
public class FieldController {
    /**
     * map to contain all character card in field controller
     */
    private Map<String, CharacterCardInFieldController> characters;
    /**
     * map to contain all skill card in field controller
     */
    private Map<String, SkillCardInFieldController> skills;
    private PlayerType playerType;

    @FXML VBox card_container;
    @FXML HBox character_container;
    @FXML HBox skill_container;
    @FXML
    CharacterCardInFieldController character1Controller;
    @FXML
    CharacterCardInFieldController character2Controller;
    @FXML
    CharacterCardInFieldController character3Controller;
    @FXML
    CharacterCardInFieldController character4Controller;
    @FXML
    CharacterCardInFieldController character5Controller;
    @FXML
    CharacterCardInFieldController character6Controller;
    @FXML
    SkillCardInFieldController skill1Controller;
    @FXML
    SkillCardInFieldController skill2Controller;
    @FXML
    SkillCardInFieldController skill3Controller;
    @FXML
    SkillCardInFieldController skill4Controller;
    @FXML
    SkillCardInFieldController skill5Controller;
    @FXML
    SkillCardInFieldController skill6Controller;


    /**
     * put all skillcard controller and character controller to a map, and registering this to eventbus
     * setting every card as null card in the beginning of the game
     */
    @FXML
    public void initialize() {
        Game.getInstance().getEventBus().register(this);
        this.characters = new HashMap<>();
        this.skills = new HashMap<>();
        characters.put("0", character1Controller);
        characters.put("1", character2Controller);
        characters.put("2", character3Controller);
        characters.put("3", character4Controller);
        characters.put("4", character5Controller);
        characters.put("5", character6Controller);
        skills.put("0", skill1Controller);
        skills.put("1", skill2Controller);
        skills.put("2", skill3Controller);
        skills.put("3", skill4Controller);
        skills.put("4", skill5Controller);
        skills.put("5", skill6Controller);
        characters.forEach((key, controller) -> {
            controller.setNullCard();
        });
        skills.forEach((key, controller) -> {
            controller.setNullCard();
        });
    }

    /**
     * render each character, and each skill card in field
     */
    public void render() {
        setNullCharacter();
        setNullSkill();
        List<CharacterCardInField> characterCardInFieldList = Game.getInstance().getPlayerByType(playerType).getField().getCharCardList();
        List<SkillCardInField> skillCardList = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList();
        for(CharacterCardInField characterCardInField : characterCardInFieldList) {
            this.characters.get(Integer.toString(characterCardInField.getIndex())).setCard(characterCardInField);
            this.characters.get(Integer.toString(characterCardInField.getIndex())).renderRotate();          // biar dia tetep sesuai keadaan dia
        }
        for(SkillCardInField skillCardInField : skillCardList) {
            this.skills.get(Integer.toString(skillCardInField.getIndex())).setCard(skillCardInField);
        }
    }

    /**
     * set all character in field null
     */
    private void setNullCharacter() {
        characters.forEach((key, controller) -> {
            controller.setNullCard();
        });
    }


    /**
     * set all skill in field null
     */
    private void setNullSkill() {
        skills.forEach((key, controller) -> {
            controller.setNullCard();
        });
    }

    /**
     * swapping the position of skill and character card for player B
     */
    public void swapCharactersAndSkillsPosition() {
        card_container.getChildren().remove(character_container);
        card_container.getChildren().add(character_container);
    }

    /**
     * set character action visible if value is true
     */
    public void setCharactersActionsVisible(boolean value) {
        this.characters.values().forEach( controller -> {
            controller.setActionVisible(value);
        });
        this.skills.values().forEach(controller -> {
            controller.setActionVisible(value);
        });
    }

    /**
     * method for setting playerType
     * @param playerType the player type
     */
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
        characters.values().forEach( controller -> {
            controller.setPlayerType(playerType);
        });
        skills.values().forEach(controller -> {
            controller.setPlayerType(playerType);
        });
    }

    /**
     Subscribe method for updating the field if the request's playertype matches
     */
    @Subscribe
    public void update(FieldRenderRequest request){
        if (request.getPlayerType().equals(playerType)){
            this.render();
        }
    }
}
