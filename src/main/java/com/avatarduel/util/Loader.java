package com.avatarduel.util;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import com.avatarduel.factory.CardFactory;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;

public class Loader {
    private static final String LAND_CSV_FILE_PATH = "../card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "../card/data/character.csv";
    private static final String SKILL_AURA_CSV_FILE_PATH = "../card/data/skill_aura.csv";
    private static final String SKILL_DESTROY_CSV_FILE_PATH = "../card/data/skill_destroy.csv";
    private static final String SKILL_POWER_UP_CSV_FILE_PATH = "../card/data/skill_power_up.csv";

    private CardFactory cardFactory;

    public Loader() {
        cardFactory = new CardFactory();
    }

    public List<String[]> loadCards(String filename) throws IOException, URISyntaxException {

        File urlCSVFile = new File(getClass().getResource(filename).toURI());
        CSVReader csvReader = new CSVReader(urlCSVFile, "\t");
        csvReader.setSkipHeader(true);
        List<String[]> csvRows = csvReader.read();
        return csvRows;
    }

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

    // cannot be used -> still have not implemented file for Destroy Skill Card
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

    // cannot be used -> still have not implemented file for PowerUp Skill Card
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
    // debugging function
    public void printLoadResult(List<Card> arr) {
        for (Card card : arr){
            card.show();
        }
    }

}
