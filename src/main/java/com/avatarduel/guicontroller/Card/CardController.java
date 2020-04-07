package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.card.*;
import com.avatarduel.model.type.CardType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public abstract class CardController {
    protected Card data;
    protected final String[] listBorderClass = {"water_border", "fire_border", "earth_border", "air_border", "null_card", "flipped_card"};
    @FXML protected VBox card_border;
    @FXML protected Label card_name;
    @FXML protected ImageView card_img;
    @FXML protected ImageView card_icon;
    @FXML protected Label card_atk;
    @FXML protected Label card_def;
    @FXML protected Label card_pow;

    public void setCard(Card card) {
        this.data = card;
        card_name.setText(this.data.getName());
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
        switch (card.getElement()) {
            case WATER:
                setBorderStyle("water_border");
                break;
            case FIRE:
                setBorderStyle("fire_border");
                break;
            case EARTH:
                setBorderStyle("earth_border");
                break;
            case AIR:
                setBorderStyle("air_border");
                break;
        }
    }
    public void setAdditionalInfoCard(CharacterCard card) {
        card_atk.setText("ATK : " + Integer.toString( card.getAttack()));
        card_def.setText("DEF : " + Integer.toString( card.getDefense()));
        card_pow.setText("POW : " + Integer.toString(this.data.getPower()));
    }

    public void setAdditionalInfoCard(SkillAuraCard card) {
        card_atk.setText("ATK : " + card.getAttack());
        card_def.setText("DEF : " + card.getDefense());
        card_pow.setText("POW : " + Integer.toString(this.data.getPower()));
    }

    public void setAdditionalInfoCard(SkillDestroyCard card) {
        card_atk.setText("des");
        card_def.setText("");
        card_pow.setText("POW : " + Integer.toString(this.data.getPower()));
    }

    public void setAdditionalInfoCard(SkillPowerUpCard card) {
        card_atk.setText("");
        card_def.setText("");
        card_pow.setText("POW: " + Integer.toString(this.data.getPower()));
    }

    public void setAdditionalInfoCard(LandCard card) {
        card_atk.setText("");
        card_def.setText("");
        card_pow.setText("");
    }

    public void setNull() {
        card_name.setText("");
        card_img.setImage(null);
        card_atk.setText("");
        card_def.setText("");
        card_pow.setText("");
        card_icon.setImage(null);
        card_border.getStyleClass().remove("flipped_card");
        card_border.getStyleClass().add("null_card");
    }

    protected void setBorderStyle(String className) {
        card_border.getStyleClass().removeAll(listBorderClass);
        card_border.getStyleClass().add(className);
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
