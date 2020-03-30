package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.avatarduel.model.Element;
import com.avatarduel.model.Land;
import com.avatarduel.util.CSVReader;

public class Main {

    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SKILL_CSV_FILE_PATH = "card/data/skill_aura.csv";


    public void loadCards(String filename) throws IOException, URISyntaxException {
        File landCSVFile = new File(getClass().getResource(filename).toURI());
        CSVReader landReader = new CSVReader(landCSVFile, "\t");
        landReader.setSkipHeader(true);
        List<String[]> landRows = landReader.read();
        for (String[] row : landRows) {
            testPrintString(row);
            Land l = new Land(row[1], row[3], Element.valueOf(row[2]));
        }
    }

    public void loadLand() {
        try{
            loadCards(LAND_CSV_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void loadCharacter() {
        try{
            loadCards(CHARACTER_CSV_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void loadSkill() {
        try{
            loadCards(SKILL_CSV_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void testPrintString(String[] arr) {
        for (String text : arr) {
            System.out.print(text + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        System.out.println("Hello World");
        Main main = new Main();
        main.loadCharacter();
        main.loadLand();
        main.loadSkill();

    }
}
