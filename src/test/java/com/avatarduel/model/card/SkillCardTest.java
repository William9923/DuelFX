package com.avatarduel.model.card;
import com.avatarduel.model.type.Element;
import com.avatarduel.model.type.CardType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkillCardTest {
    private SkillCard Card;

    public SkillCardTest() {
        this.Card = new SkillCard(0, "sample name", Element.WATER, "lorem ipsum", "imgfile");
    }

    @Test
    public void Test() {
        assertEquals(0, Card.getId(), "id salah");
        assertEquals("sample name", Card.getName(), "nama tidak sesuai");
        assertEquals(Element.WATER, Card.getElement(), "elemen tidak sesuai");
        assertEquals("lorem ipsum", Card.getDescription(), "dekripsi tidak sesuai");
        assertEquals("imgfile", Card.getImage(), "file image tidak sesuai");
    }

}
