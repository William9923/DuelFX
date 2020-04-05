package com.avatarduel.guicontroller.Card;

import com.avatarduel.model.card.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.File;

public class DisplayCardController extends  CardController {
    @FXML private AnchorPane card_border;
    @FXML private Label card_desc;
    @FXML private HBox card_attributes;

    @Override
    public void setCard(Card card) {
        super.setCard(card);
        card_desc.setText(this.data.getDescription());
        card_img.imageProperty().set(getImage(card.getImage()));
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
