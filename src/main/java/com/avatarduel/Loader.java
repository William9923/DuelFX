package com.avatarduel;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import com.avatarduel.model.Element;
import com.avatarduel.util.CSVReader;

public class Loader {
    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SKILL_CSV_FILE_PATH = "card/data/skill_aura.csv";

    public List<String[]> loadCards(String filename) throws IOException, URISyntaxException {

        File urlCSVFile = new File(getClass().getResource(filename).toURI());
        CSVReader csvReader = new CSVReader(urlCSVFile, "\t");
        csvReader.setSkipHeader(true);
        List<String[]> csvRows = csvReader.read();
        return csvRows;
    }

    public List<String[]> loadLand() {
        try{
            return loadCards(LAND_CSV_FILE_PATH);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String[]> loadCharacter() {
        try{
            return loadCards(CHARACTER_CSV_FILE_PATH);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String[]> loadSkill() {
        try{
            return loadCards(SKILL_CSV_FILE_PATH);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printLoadResult(List<String[]> arr) {
        for (String[] text : arr){
            for (String txt : text) {
                System.out.print(txt + "  ");
            }
            System.out.println();
        }
    }

    public static Element outputElement(String element) {
        switch (element) {
            case "WATER" : return Element.WATER;
            case "AIR" : return Element.AIR;
            case "EARTH" : return Element.EARTH;
            case "FIRE" : return Element.FIRE;
            default: return Element.AIR; // blom di handle
        }
    }

}
