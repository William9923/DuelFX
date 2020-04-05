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
    protected final String[] listBorderClass = {"water_border", "fire_border", "earth_border", "air_border"};
    @FXML protected VBox card_border;
    @FXML protected Label card_name;
    @FXML protected ImageView card_img;
    @FXML protected Label card_atk;
    @FXML protected Label card_def;
    @FXML protected Label card_pow;

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

    public void setAdditionalInfoCard(LandCard card) {
        card_atk.setText("");
        card_def.setText("");
        card_pow.setText("");
    }

    public void setCard(Card card) {
        this.data = card;
        card_name.setText(this.data.getName());
        card_img.imageProperty().set(getImage(card.getImage()));
        switch (card.getType()) {
            case CHARACTER: setAdditionalInfoCard((CharacterCard) card); break;
            case LAND: setAdditionalInfoCard((LandCard) card); break;
            case SKILL_AURA: setAdditionalInfoCard((SkillAuraCard) card); break;
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

    private void setBorderStyle(String className) {
        card_border.getStyleClass().removeAll(listBorderClass);
        card_border.getStyleClass().add(className);
    }

    private Image getImage(String imgpath) {
        File f = new File("build/resources/main/com/avatarduel/card/" + imgpath.substring(8));
        return new Image("file:\\" + f.getAbsolutePath());
    }
}
