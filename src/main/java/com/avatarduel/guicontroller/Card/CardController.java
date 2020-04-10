package com.avatarduel.guicontroller.Card;

import com.avatarduel.guicontroller.Request.ShowSelectedCardRequest;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;

public abstract class CardController {
    protected Card cardData;
    protected final String[] listBorderClass = {"water_border", "fire_border", "earth_border", "air_border", "null_card", "flipped_card"};

    @FXML protected VBox card_border;
    @FXML protected Label card_name;
    @FXML protected ImageView card_img;
    @FXML protected ImageView card_icon;
    @FXML protected Label card_atk;
    @FXML protected Label card_def;
    @FXML protected Label card_pow;

    public void setCard(Card card) {
        this.cardData = card;
        card_name.setText(this.cardData.getName());
        card_img.imageProperty().set(getImage(card.getImage()));
        switch (card.getType()) {
            case CHARACTER:
                setAdditionalInfoCard((CharacterCard) card);
                card_icon.setImage(getIcon("character_icon.png"));
                break;
            case LAND:
                setAdditionalInfoCard((LandCard) card);
                card_icon.setImage(getIcon("land_icon.png"));
                break;
            case SKILL_AURA:
                setAdditionalInfoCard((SkillAuraCard) card);
                card_icon.setImage(getIcon("skill_icon.png"));
                break;
            case SKILL_DESTROY:
                setAdditionalInfoCard((SkillDestroyCard) card);
                card_icon.setImage(getIcon("skill_icon.png"));
                break;
            case SKILL_POWER_UP:
                setAdditionalInfoCard((SkillPowerUpCard) card);
                card_icon.setImage(getIcon("skill_icon.png"));
        }
        removeAllBorderStyle();
        switch (card.getElement()) {
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
        }
    }
    public void setAdditionalInfoCard(CharacterCard card) {
        card_atk.setText("ATK : " + Integer.toString( card.getAttack()));
        card_def.setText("DEF : " + Integer.toString( card.getDefense()));
        card_pow.setText("POW : " + Integer.toString(this.cardData.getPower()));
    }

    public void setAdditionalInfoCard(SkillAuraCard card) {
        card_atk.setText("ATK : " + card.getAttack());
        card_def.setText("DEF : " + card.getDefense());
        card_pow.setText("POW : " + Integer.toString(this.cardData.getPower()));
    }

    public void setAdditionalInfoCard(SkillDestroyCard card) {
        card_atk.setText("des");
        card_def.setText("");
        card_pow.setText("POW : " + Integer.toString(this.cardData.getPower()));
    }

    public void setAdditionalInfoCard(SkillPowerUpCard card) {
        card_atk.setText("");
        card_def.setText("");
        card_pow.setText("POW: " + Integer.toString(this.cardData.getPower()));
    }

    public void setAdditionalInfoCard(LandCard card) {
        card_atk.setText("");
        card_def.setText("");
        card_pow.setText("");
    }

    public void setNullCard() {
        card_name.setText("");
        card_img.setImage(null);
        card_atk.setText("");
        card_def.setText("");
        card_pow.setText("");
        card_icon.setImage(null);
        card_border.getStyleClass().remove("flipped_card");
        card_border.getStyleClass().add("null_card");
    }

    protected void addBorderStyle(String className) {
        card_border.getStyleClass().add(className);
    }

    protected void removeBorderStyle(String className) {
        card_border.getStyleClass().remove(className);
    }

    protected void removeAllBorderStyle() {
        card_border.getStyleClass().removeAll(listBorderClass);
    }

    public Card getCardData() {
        return cardData;
    }

    private Image getImage(String imgpath) {
        File f = new File("build/resources/main/com/avatarduel/card/" + imgpath.substring(8));
        return new Image("file:\\" + f.getAbsolutePath());
    }

    private Image getIcon(String iconname) {
        File f = new File("src/main/resources/com/avatarduel/card/icon/" + iconname);
        return new Image("file:\\" + f.getAbsolutePath());
    }
}
