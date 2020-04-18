package com.avatarduel.model.card;
import com.avatarduel.model.type.Element;
import com.avatarduel.model.type.PlayerType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardInHandTest {
    private CardInHand CIH;

    public CardInHandTest() {
        Card temp = new Card(0, "sample name", Element.AIR, "lorem ipsum", "imgfile");
        PlayerType tempPlayer = PlayerType.A;
        this.CIH = new CardInHand(temp, tempPlayer);
    }

    @Test
    public void getPlayerType() {
        assertEquals(PlayerType.A, CIH.getPlayerType());
    }

    @Test
    public void getCard() {
        assertEquals(0, CIH.getCard().getId());
        assertEquals("sample name", CIH.getCard().getName());
        assertEquals(Element.AIR, CIH.getCard().getElement());
        assertEquals("lorem ipsum", CIH.getCard().getDescription());
        assertEquals("imgfile", CIH.getCard().getImage());

    }
}
