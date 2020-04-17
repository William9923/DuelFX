package com.avatarduel.dao;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class terjaditerTestCSVCardDAO {

    @Test
    public void testCardRetrieval() {
        CSVCardDAO tester = new CSVCardDAO(); // MyClass is tested

        // assert statements
        assertEquals("Kartu Database kosong",true, tester.getAllCard().size() > 0);
        assertEquals("Database Kartu Karakter kosong", true, tester.getAllCharacterCard().size() > 0);
        assertEquals("Database Kartu Land kosong", true, tester.getAllLandCard().size() > 0);
        assertEquals("Database Kartu Skill kosong", true, tester.getAllSkillCard().size() > 0);

    }
}
