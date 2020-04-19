package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.card.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;

/**
 * used as an abstract class for setting a card's information for GUI, such as
 * its attack, defense, name, image, icon, etc
 * @author G10-K03-CardGameOOP
 */
public abstract class CardController {
    protected Card cardData;
    /**
     * used for removeAll method ( more see removeAllBorderStyle method )
     */
    protected final String[] listBorderClass = {"water_border", "energy_border", "fire_border", "earth_border", "air_border", "null_card", "flipped_card"};

    @FXML protected VBox card_border;
    @FXML protected Label card_name;
    @FXML protected ImageView card_img;
    @FXML protected ImageView card_icon;
    @FXML protected Label card_atk;
    @FXML protected Label card_def;
    @FXML protected Label card_pow;

    /**
     * method for setting the card's data and its additional info
     * @param card the player type
     */
    public void setCard(Card card) {
        this.cardData = card;
        card_name.setText(this.cardData.getName());
        card_img.imageProperty().set(getImage(card.getImage()));
        setAdditionalInfo();
        removeAllBorderStyle();
        setBorder();
    }

    /**
     * method for setting additional card info from its card type, and managing card icons
     */
    public void setAdditionalInfo() {
        switch (cardData.getType()) {
            case CHARACTER:
                setAdditionalInfoCard((CharacterCard) cardData);
                card_icon.setImage(getIcon("character_icon.png"));
                break;
            case LAND:
                setAdditionalInfoCard((LandCard) cardData);
                card_icon.setImage(getIcon("land_icon.png"));
                break;
            case SKILL_AURA:
                setAdditionalInfoCard((SkillAuraCard) cardData);
                card_icon.setImage(getIcon("skill_icon.png"));
                break;
            case SKILL_DESTROY:
                setAdditionalInfoCard((SkillDestroyCard) cardData);
                card_icon.setImage(getIcon("skill_icon.png"));
                break;
            case SKILL_POWER_UP:
                setAdditionalInfoCard((SkillPowerUpCard) cardData);
                card_icon.setImage(getIcon("skill_icon.png"));
        }
    }

    /**
     * method for setting the card's border based on its element type
     */
    public void setBorder() {
        switch (cardData.getElement()) {
            case WATER:
                addBorderStyle("water_border");
                break;
            case FIRE:
                addBorderStyle("fire_border");
                break;
            case EARTH:
                addBorderStyle("earth_border");
                break;
            case AIR:
                addBorderStyle("air_border");
                break;
            case ENERGY:
                addBorderStyle("energy_border");
                break;
        }
    }

    /**
     * method for setting a character card's atk, def, and pow info
     * @param card the character card
     */
    public void setAdditionalInfoCard(CharacterCard card) {
        card_atk.setText("ATK : " + Integer.toString( card.getAttack()));
        card_def.setText("DEF : " + Integer.toString( card.getDefense()));
        card_pow.setText("POW : " + Integer.toString(this.cardData.getPower()));
    }

    /**
     * method for setting a skill aura card's atk, def, and pow info
     * @param card the skill aura card
     */
    public void setAdditionalInfoCard(SkillAuraCard card) {
        card_atk.setText("ATK : " + card.getAttack());
        card_def.setText("DEF : " + card.getDefense());
        card_pow.setText("POW : " + Integer.toString(this.cardData.getPower()));
    }

    /**
     * method for setting a skill destroy card's pow info
     * @param card the skill destroy card
     */
    public void setAdditionalInfoCard(SkillDestroyCard card) {
        card_atk.setText("des");
        card_def.setText("");
        card_pow.setText("POW : " + Integer.toString(this.cardData.getPower()));
    }

    /**
     * method for setting a skill power up card's pow info
     * @param card the skill power up card
     */
    public void setAdditionalInfoCard(SkillPowerUpCard card) {
        card_atk.setText("pow");
        card_def.setText("");
        card_pow.setText("POW: " + Integer.toString(this.cardData.getPower()));
    }

    /**
     * method for setting a land card info
     * @param card the land card
     */
    public void setAdditionalInfoCard(LandCard card) {
        card_atk.setText("");
        card_def.setText("");
        card_pow.setText("");
    }

    /**
     * set the text, image, etc of the card to null or empty string and make the style "null_card"
     */
    public void setNullCard() {
        card_name.setText("");
        card_img.setImage(null);
        card_atk.setText("");
        card_def.setText("");
        card_pow.setText("");
        card_icon.setImage(null);
        card_border.getStyleClass().remove("flipped_card");
        card_border.getStyleClass().add("null_card");
        cardData = null;
    }

    /**
     * method for adding a css style to the card border
     * @param className the border classname
     */
    protected void addBorderStyle(String className) {
        card_border.getStyleClass().add(className);
    }

    /**
     * method for removing all border style
     */
    protected void removeAllBorderStyle() {
        card_border.getStyleClass().removeAll(listBorderClass);
    }

    /**
     * getter method for cardData
     */
    public Card getCardData() {
        return cardData;
    }

    /**
     * getter method for getting card image
     */
    private Image getImage(String imgpath) {
        File f = new File("build/resources/main/com/avatarduel/card/" + imgpath.substring(8));
        return new Image("file:\\" + f.getAbsolutePath());
    }

    /**
     * getter method for getting card icon
     */
    private Image getIcon(String iconname) {
        File f = new File("src/main/resources/com/avatarduel/card/icon/" + iconname);
        return new Image("file:\\" + f.getAbsolutePath());
    }
}
