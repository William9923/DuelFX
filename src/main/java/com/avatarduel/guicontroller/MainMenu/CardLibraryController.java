package com.avatarduel.guicontroller.MainMenu;

import com.avatarduel.guicontroller.Card.DisplayCardController;
import com.avatarduel.model.card.Card;
import com.avatarduel.util.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

/**
 * used to manage the card display slideshow on the menu option
 */
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
    private Window window;

    List<Card> characterCards;
    List<Card> skillAuraCards;
    List<Card> skillPowerUpCards;
    List<Card> skillDestroyCards;
    List<Card> landCards;

    /**
     * initialize all list of cards
     */
    @FXML
    public void initialize() {
        Loader loader = new Loader();
        characterCards = loader.loadCharacter();
        skillAuraCards      = loader.loadSkillAura();
        skillDestroyCards   = loader.loadSkillDestroy();
        skillPowerUpCards   = loader.loadSkillPowerUp();
        landCards           = loader.loadLand();
        charIndex = 1;
        auraIndex = 1;
        powerUpIndex = 1;
        destroyIndex = 1;
        landIndex = 1;
    }

    /**
     * set the display to next character
     */
    @FXML
    protected void nextCharacter() {
        if(charIndex >= characterCards.size()) {
            charIndex = 1;
        }
        displayCardController.setCard(characterCards.get(charIndex));
        charIndex++;
    }

    /**
     * set the display to next skill destroy
     */
    @FXML
    protected void nextSkillDestroy() {
        if(destroyIndex >= skillDestroyCards.size()) {
            destroyIndex = 1;
        }
        displayCardController.setCard(skillDestroyCards.get(destroyIndex));
        destroyIndex++;
    }

    /**
     * set the display to next skill power up
     */
    @FXML
    protected void nextSkillPowerUp() {
        if(powerUpIndex >= skillPowerUpCards.size()) {
            powerUpIndex = 1;
        }
        displayCardController.setCard(skillPowerUpCards.get(powerUpIndex));
        powerUpIndex++;
    }

    /**
     * set the display to next skill aura
     */
    @FXML
    protected void nextSkillAura() {
        if(auraIndex >= skillAuraCards.size()) {
            auraIndex = 1;
        }
        displayCardController.setCard(skillAuraCards.get(auraIndex));
        auraIndex++;
    }

    /**
     * set the display to next land
     */
    @FXML
    protected void nextLand() {
        if(landIndex >= landCards.size()) {
            landIndex = 1;
        }
        displayCardController.setCard(landCards.get(landIndex));
        landIndex++;
    }

    /**
     * hide this screen
     */
    @FXML
    protected void exit() {
        this.popup.hide();
    }

    /**
     * set the stage
     */
    public void setWindow(Window window) {
        this.window = window;
    }

    public void start() {
        this.popup = new Popup();
        popup.getContent().add(container);
        popup.show(window);
    }
}
