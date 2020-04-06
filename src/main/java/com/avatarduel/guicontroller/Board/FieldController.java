package com.avatarduel.guicontroller.Board;

import com.avatarduel.guicontroller.Card.CardOnPlayController;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCard;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldController {
    private Map<String, CardOnPlayController> characters;
    private Map<String, CardOnPlayController> skills;
    private PlayerType playerType;

    @FXML CardOnPlayController character1Controller;
    @FXML CardOnPlayController character2Controller;
    @FXML CardOnPlayController character3Controller;
    @FXML CardOnPlayController character4Controller;
    @FXML CardOnPlayController character5Controller;
    @FXML CardOnPlayController character6Controller;
    @FXML CardOnPlayController character7Controller;
    @FXML CardOnPlayController character8Controller;
    @FXML CardOnPlayController character9Controller;
    @FXML CardOnPlayController skill1Controller;
    @FXML CardOnPlayController skill2Controller;
    @FXML CardOnPlayController skill3Controller;
    @FXML CardOnPlayController skill4Controller;
    @FXML CardOnPlayController skill5Controller;
    @FXML CardOnPlayController skill6Controller;
    @FXML CardOnPlayController skill7Controller;
    @FXML CardOnPlayController skill8Controller;
    @FXML CardOnPlayController skill9Controller;

    @FXML
    public void initialize() {
        this.characters = new HashMap<String, CardOnPlayController>();
        this.skills = new HashMap<String, CardOnPlayController>();
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
        List<SkillCard> skillCardList = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList();
        int i = 0;
        while(characterCardInFieldList.get(i) != null) {
            this.characters.get(Integer.toString(i)).setCard(characterCardInFieldList.get(i));
            i++;
        }
        i = 0;
        while(skillCardList.get(i) != null) {
            this.skills.get(Integer.toString(i)).setCard(skillCardList.get(i));
            i++;
        }
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
