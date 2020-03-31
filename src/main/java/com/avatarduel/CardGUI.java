package com.avatarduel.GUIElement;
/*
 * TODO: MASIH BELUM BERES, MASIH GABISA DIJALANIN YG INI
 * */
import com.avatarduel.util.CSVReader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.print.URIException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CardGUI {
    private static final String CHARACTER_CSV_FILE_PATH = "../card/data/character.csv";
    private static final String CARD_CSS_PATH = "../card/card.css";
    private Scene card;

    public CardGUI(String CardName) {
        try {
            File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());

            CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
            characterReader.setSkipHeader(true);
            List<String[]> characterRows = characterReader.read();
            // INDEX CHARACTER.CSV : id(0) name(1) 2.element 3.description 4.image path 5.attack 6.defense 7.power
            String[] firstCharacter = characterRows.get(0);
            Text cardTitle = new Text(firstCharacter[1]);
            // FORMAT IMAGE PATH : src/res/image/character/Kuruk.pn

            System.out.println(getClass().getResource("card/" + firstCharacter[4].substring(8)).getPath());
            Text description = new Text(0, 100, firstCharacter[3]);
            Text ATK = new Text(0, 0, ("ATK : " + firstCharacter[5]));
            Text DEF = new Text(0, 0, "DEF : " + firstCharacter[6]);
            Text POW = new Text(0, 0, "POW : " + firstCharacter[7]);
            Rectangle rect = new Rectangle(100, 100);

            HBox cardAttributes = new HBox(8);
            cardAttributes.getChildren().addAll(ATK, DEF, POW);
            cardAttributes.setAlignment(Pos.CENTER);

            VBox card = new VBox(8);
            //, new ImageView(cardImage)
            card.getChildren().addAll(cardTitle, description, cardAttributes);
            card.setAlignment(Pos.CENTER);

            this.card = new Scene(card, 300, 400);

            try {
                this.card.getStylesheets().add(getClass().getResource("card/Card.css").toExternalForm());
                System.out.println(getClass().getResource(CARD_CSS_PATH).toURI());
            } catch (Exception e) {
                System.out.println("gasabi");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public Scene getCardScene() {
        return this.card;
    }

}
