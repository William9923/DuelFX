package com.avatarduel.guicontroller.Board;

import com.avatarduel.event.AttackEvent;
import com.avatarduel.guicontroller.Card.SkillCardOnPlayController;
import com.avatarduel.guicontroller.Card.CharacterCardOnPlayController;
import com.avatarduel.guicontroller.Server.GameServer;
import com.avatarduel.guicontroller.Server.subscriber.Subscriber;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.type.PlayerType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldController implements Subscriber {
    private Map<String, CharacterCardOnPlayController> characters;
    private Map<String, SkillCardOnPlayController> skills;
    private PlayerType playerType;
    private FieldController enemyFieldController;
    private int fromAttack;

    @FXML VBox popup;
    @FXML Label card_from;
    @FXML TextArea enemy_index;

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
    private GameServer gameServer;

    @FXML
    public void initialize() {
        this.characters = new HashMap<String, CharacterCardOnPlayController>();
        this.skills = new HashMap<String, SkillCardOnPlayController>();
        popup.setVisible(false);
        characters.put("0", character1Controller);
        characters.put("1", character2Controller);
        characters.put("2", character3Controller);
        characters.put("3", character4Controller);
        characters.put("4", character5Controller);
        characters.put("5", character6Controller);
        characters.put("6", character7Controller);
        characters.put("7", character8Controller);
        characters.put("8", character9Controller);
        skills.put("0", skill1Controller);
        skills.put("1", skill2Controller);
        skills.put("2", skill3Controller);
        skills.put("3", skill4Controller);
        skills.put("4", skill5Controller);
        skills.put("5", skill6Controller);
        skills.put("6", skill7Controller);
        skills.put("7", skill8Controller);
        skills.put("8", skill9Controller);
        characters.forEach((key, controller) -> {
            controller.setNull();
            controller.setIndex(Integer.parseInt(key));
            controller.setFieldController(this);
        });
        skills.forEach((key, controller) -> {
            controller.setNull();
            controller.setPlayerType(playerType);
            controller.setIndex(Integer.parseInt(key));
            controller.setFieldController(this);
        });
    }

    public void render() {
        List<CharacterCardInField> characterCardInFieldList = Game.getInstance().getPlayerByType(playerType).getField().getCharCardList();
        List<SkillCardInField> skillCardList = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList();
        for(CharacterCardInField characterCardInField : characterCardInFieldList) {
            this.characters.get(Integer.toString(characterCardInField.getIndex())).setCard(characterCardInField);
        }
    }

    public void swapCharactersAndSkillsPosition() {
        card_container.getChildren().remove(character_container);
        card_container.getChildren().add(character_container);
    }

    public CharacterCardOnPlayController getCharacterCardController(int index) {
        return this.characters.get(Integer.toString(index));
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
        characters.forEach((key,controller) -> {
            controller.setPlayerType(playerType);
        });
        skills.forEach((key,controller) -> {
            controller.setPlayerType(playerType);
        });
    }

    public void setEnemyFieldController(FieldController enemyFieldController) {
        this.enemyFieldController = enemyFieldController;
    }

    @FXML
    public void submitAttackRequestForm() {
        int indexOfEnemyCard = Integer.parseInt(enemy_index.getText());
        int enemyTargetId = enemyFieldController.getCharacterCardController(indexOfEnemyCard).getCharacterCardInField().getCard().getId();
        AttackEvent attackEvent = new AttackEvent(fromAttack , enemyTargetId, this.playerType, enemyFieldController.playerType);
        System.out.println(fromAttack);
        System.out.println(enemyTargetId);
        System.out.println(this.playerType);
        System.out.println(enemyFieldController.playerType);
        System.out.println(attackEvent.validate());
        attackEvent.execute();
        this.render();
        this.enemyFieldController.render();
        this.popup.setVisible(false);
    }

    public void showAttackRequestForm(CharacterCardInField characterCardInField) {
        this.popup.setVisible(true);
        fromAttack = characterCardInField.getCard().getId();
        CharacterCard characterCard = (CharacterCard) characterCardInField.getCard();
        this.card_from.setText("Attacking from card " + characterCard.getName() + " with attack : "
                + Integer.toString(characterCard.getAttack()));

    }

    public void setGameServer(GameServer gameServer) {
        this.gameServer = gameServer;
        for (CharacterCardOnPlayController characterCardOnPlayController : characters.values()) {
            characterCardOnPlayController.setGameServer(gameServer);
        }
    }
}
