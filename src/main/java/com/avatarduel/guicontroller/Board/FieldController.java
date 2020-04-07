package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Card.SkillCardOnPlayController;
import com.avatarduel.guicontroller.Card.CharacterCardOnPlayController;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldController {
    private Map<String, CharacterCardOnPlayController> characters;
    private Map<String, SkillCardOnPlayController> skills;
    private PlayerType playerType;

    @FXML VBox card_container;
    @FXML HBox character_container;
    @FXML HBox skill_container;
    @FXML CharacterCardOnPlayController character1Controller;
    @FXML CharacterCardOnPlayController character2Controller;
    @FXML CharacterCardOnPlayController character3Controller;
    @FXML CharacterCardOnPlayController character4Controller;
    @FXML CharacterCardOnPlayController character5Controller;
    @FXML CharacterCardOnPlayController character6Controller;
    @FXML CharacterCardOnPlayController character7Controller;
    @FXML CharacterCardOnPlayController character8Controller;
    @FXML CharacterCardOnPlayController character9Controller;
    @FXML
    SkillCardOnPlayController skill1Controller;
    @FXML
    SkillCardOnPlayController skill2Controller;
    @FXML
    SkillCardOnPlayController skill3Controller;
    @FXML
    SkillCardOnPlayController skill4Controller;
    @FXML
    SkillCardOnPlayController skill5Controller;
    @FXML
    SkillCardOnPlayController skill6Controller;
    @FXML
    SkillCardOnPlayController skill7Controller;
    @FXML
    SkillCardOnPlayController skill8Controller;
    @FXML
    SkillCardOnPlayController skill9Controller;

    @FXML
    public void initialize() {
        this.characters = new HashMap<String, CharacterCardOnPlayController>();
        this.skills = new HashMap<String, SkillCardOnPlayController>();
        characters.put("1", character1Controller);
        characters.put("2", character2Controller);
        characters.put("3", character3Controller);
        characters.put("4", character4Controller);
        characters.put("5", character5Controller);
        characters.put("6", character6Controller);
        characters.put("7", character7Controller);
        characters.put("8", character8Controller);
        characters.put("9", character9Controller);
        skills.put("1", skill1Controller);
        skills.put("2", skill2Controller);
        skills.put("3", skill3Controller);
        skills.put("4", skill4Controller);
        skills.put("5", skill5Controller);
        skills.put("6", skill6Controller);
        skills.put("7", skill7Controller);
        skills.put("8", skill8Controller);
        skills.put("9", skill9Controller);
        characters.forEach((key, controller) -> {
            controller.setNull();
        });
        skills.forEach((key, controller) -> {
            controller.setNull();
        });
    }

    public void render() {
        List<CharacterCardInField> characterCardInFieldList = Game.getInstance().getPlayerByType(playerType).getField().getCharCardList();
        List<SkillCardInField> skillCardList = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList();
        int i = 0;
        while(characterCardInFieldList.get(i) != null) {
            this.characters.get(Integer.toString(i)).setCard(characterCardInFieldList.get(i));
            i++;
        }
        i = 0;
        while(skillCardList.get(i) != null) {
            this.skills.get(Integer.toString(i)).setCard(skillCardList.get(i).getCard());
            i++;
        }
    }

    public void swapCharactersAndSkillsPosition() {
        card_container.getChildren().remove(character_container);
        card_container.getChildren().add(character_container);
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
