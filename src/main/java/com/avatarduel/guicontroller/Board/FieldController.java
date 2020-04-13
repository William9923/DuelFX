package com.avatarduel.guicontroller.Board;

import com.avatarduel.event.AttackEvent;
import com.avatarduel.guicontroller.Card.CharacterCardInFieldController;
import com.avatarduel.guicontroller.Card.SkillCardInFieldController;
import com.avatarduel.guicontroller.Request.FieldRenderRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.type.PlayerType;
import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldController {
    private Map<String, CharacterCardInFieldController> characters;
    private Map<String, SkillCardInFieldController> skills;
    private PlayerType playerType;
    private FieldController enemyFieldController;
    private int fromAttack;

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

    @FXML
    public void initialize() {
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
            controller.setPlayerType(playerType);
            controller.setIndex(Integer.parseInt(key));
        });
    }

    private void setNullCharacter() {
        characters.forEach((key, controller) -> {
            controller.setNullCard();
        });
    }

    private void setNullSkill() {
        skills.forEach((key, controller) -> {
            controller.setNullCard();
        });
    }

    public void render() {
        setNullCharacter();
        setNullSkill();
        List<CharacterCardInField> characterCardInFieldList = Game.getInstance().getPlayerByType(playerType).getField().getCharCardList();
        List<SkillCardInField> skillCardList = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList();
        for(CharacterCardInField characterCardInField : characterCardInFieldList) {
            this.characters.get(Integer.toString(characterCardInField.getIndex())).setCard(characterCardInField);
            this.characters.get(Integer.toString(characterCardInField.getIndex())).renderRotate();          // biar dia tetep sesuai keadaan dia
        }
        System.out.println("Updating skill action render : ");
        System.out.println("Skill card list : " + skillCardList.size());
        for(SkillCardInField skillCardInField : skillCardList) {
            System.out.println("Index : " + skillCardInField.getIndex());
            this.skills.get(Integer.toString(skillCardInField.getIndex())).setCard(skillCardInField);
        }
    }

    public void swapCharactersAndSkillsPosition() {
        card_container.getChildren().remove(character_container);
        card_container.getChildren().add(character_container);
    }

    public void setCharactersActionsVisible(boolean value) {
        this.characters.values().forEach( controller -> {
            controller.setActionVisible(value);
        });
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
        characters.values().forEach( controller -> {
            controller.setPlayerType(playerType);
        });
    }

    @FXML
    public void submitAttackRequestForm() {
    }

    @Subscribe
    public void update(FieldRenderRequest request){
        if (request.getPlayerType().equals(playerType)){
            System.out.println("Updating Field");
            System.out.println(playerType);
            this.render();
        }
    }
}
