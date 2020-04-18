package com.avatarduel.guicontroller.MainMenu;

import com.avatarduel.guicontroller.Card.DisplayCardController;
import com.avatarduel.model.*;
import com.avatarduel.model.card.*;
import com.avatarduel.util.CSVReader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CardLibraryController {
    @FXML private Button see_char;
    @FXML private Button see_aura;
    @FXML private Button see_powerup;
    @FXML private Button see_destroy;
    @FXML private Button see_land;
    @FXML private AnchorPane container;
    @FXML private DisplayCardController displayCardController;

    private int charIndex;
    private int auraIndex;
    private int powerUpIndex;
    private int destroyIndex;
    private int landIndex;
    private Stage stage;
    private Popup popup;

    List<String[]> characterRows;
    List<String[]> auraRows;
    List<String[]> powerUpRows;
    List<String[]> destroyRows;
    List<String[]> landRows;

    public CardLibraryController() {
        try {
            characterRows = getCSVListFrom(getCSVPathFrom("character.csv"));
            auraRows = getCSVListFrom(getCSVPathFrom("skill_aura.csv"));
            destroyRows = getCSVListFrom(getCSVPathFrom("skill_destroy.csv"));
            powerUpRows = getCSVListFrom(getCSVPathFrom("skill_power_up.csv"));
            landRows = getCSVListFrom(getCSVPathFrom("land.csv"));
        }
        catch (IOException IOE) {
            System.out.println("Library controller : getCharacters");
        }
        charIndex = 1;
        auraIndex = 1;
        powerUpIndex = 1;
        destroyIndex = 1;
        landIndex = 1;
    }

    @FXML
    public void initialize() {
    }

    @FXML
    protected void nextCharacter() {
        if(charIndex >= characterRows.size()) {
            charIndex = 1;
        }
        displayCardController.setCard(new CharacterCard(characterRows.get(charIndex)));
        charIndex++;
    }

    @FXML
    protected void nextSkillDestroy() {
        if(destroyIndex >= destroyRows.size()) {
            destroyIndex = 1;
        }
        displayCardController.setCard(new SkillDestroyCard(destroyRows.get(destroyIndex)));
        destroyIndex++;
    }

    @FXML
    protected void nextSkillPowerUp() {
        if(powerUpIndex >= powerUpRows.size()) {
            powerUpIndex = 1;
        }
        displayCardController.setCard(new SkillPowerUpCard(powerUpRows.get(powerUpIndex)));
        powerUpIndex++;
    }

    @FXML
    protected void nextSkillAura() {
        if(auraIndex >= auraRows.size()) {
            auraIndex = 1;
        }
        displayCardController.setCard(new SkillAuraCard(auraRows.get(auraIndex)));
        auraIndex++;
    }

    @FXML
    protected void nextLand() {
        if(landIndex >= landRows.size()) {
            landIndex = 1;
        }
        displayCardController.setCard(new LandCard(landRows.get(landIndex)));
        landIndex++;
    }

    @FXML
    protected  void exit() {
        this.popup.hide();
    }

    private List<String[]> getCSVListFrom(String filePath) throws IOException {
        File CSVFile = new File(filePath);
        CSVReader reader = new CSVReader(CSVFile, "\t");
        return reader.read();
    }

    private String getCSVPathFrom(String fileName) {
        return "src/main/resources/com/avatarduel/card/data/" + fileName;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        this.popup = new Popup();
        popup.getContent().add(container);
        popup.show(stage);
    }
}
