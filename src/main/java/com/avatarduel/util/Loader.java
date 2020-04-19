package com.avatarduel.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import com.avatarduel.factory.CardFactory;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.type.CardType;

/**
 * Loader is a class to load the data from CSVReader into card objects
 *
 * @author K10
 */
public class Loader {
    public static final String LAND_CSV_FILE_PATH = "../card/data/land.csv";
    public static final String CHARACTER_CSV_FILE_PATH = "../card/data/character.csv";
    public static final String SKILL_AURA_CSV_FILE_PATH = "../card/data/skill_aura.csv";
    public static final String SKILL_DESTROY_CSV_FILE_PATH = "../card/data/skill_destroy.csv";
    public static final String SKILL_POWER_UP_CSV_FILE_PATH = "../card/data/skill_power_up.csv";

    /**
     * CardFactory is used to craete cards from the data from csvreader
     */
    private CardFactory cardFactory;

    public Loader() {
        cardFactory = new CardFactory();
    }

    /**
     * Load cards with csvReader and returns its data and skip header
     * @param filename name of the file
     * @return List of string array, each represents a card's data
     */
    public List<String[]> loadCards(String filename) throws IOException, URISyntaxException {
        File urlCSVFile = new File(getClass().getResource(filename).toURI());
        CSVReader csvReader = new CSVReader(urlCSVFile, "\t");
        csvReader.setSkipHeader(true);
        List<String[]> csvRows = csvReader.read();
        return csvRows;
    }

    /**
     * Load land cards from land.csv
     * @return list of land cards
     */
    public List<Card> loadLand() {
        try{
            List<String[]> list =  loadCards(LAND_CSV_FILE_PATH);
            List<Card> listCard = new ArrayList<Card>();
            CardType type = CardType.LAND;

            for (String[] component: list) {
                listCard.add(cardFactory.createCard(component, type));
            }
            return listCard;

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Load character cards from character.csv
     * @return list of character cards
     */
    public List<Card> loadCharacter() {
        try{
            List<String[]> list =  loadCards(CHARACTER_CSV_FILE_PATH);
            List<Card> listCard = new ArrayList<Card>();
            CardType type = CardType.CHARACTER;

            for (String[] component: list) {
                listCard.add(cardFactory.createCard(component, type));
            }
            return listCard;

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Load skill aura cards from skill_aura.csv
     * @return list of skill aura cards
     */
    public List<Card> loadSkillAura() {
        try{
            List<String[]> list =  loadCards(SKILL_AURA_CSV_FILE_PATH);
            List<Card> listCard = new ArrayList<Card>();
            CardType type = CardType.SKILL_AURA;

            for (String[] component: list) {
                listCard.add(cardFactory.createCard(component, type));
            }
            return listCard;

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Load skill destroy cards from skill_destroy.csv
     * @return list of skill dsetroy cards
     */
    public List<Card> loadSkillDestroy() {
        try{
            List<String[]> list =  loadCards(SKILL_DESTROY_CSV_FILE_PATH);
            List<Card> listCard = new ArrayList<Card>();
            CardType type = CardType.SKILL_DESTROY;

            for (String[] component: list) {
                listCard.add(cardFactory.createCard(component, type));
            }
            return listCard;

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Load skill power up cards from skill_power_up.csv
     * @return list of skill power up cards
     */
    public List<Card> loadSkillPowerUp() {
        try{
            List<String[]> list =  loadCards(SKILL_POWER_UP_CSV_FILE_PATH);
            List<Card> listCard = new ArrayList<Card>();
            CardType type = CardType.SKILL_POWER_UP;

            for (String[] component: list) {
                listCard.add(cardFactory.createCard(component, type));
            }
            return listCard;

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
