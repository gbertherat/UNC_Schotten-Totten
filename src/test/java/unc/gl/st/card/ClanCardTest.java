package unc.gl.st.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClanCardTest {
    private static ClanCard card;

    @BeforeAll
    public static void setUp() throws Exception{
        card = new ClanCard(5, Color.BLUE);
    }

    @Test
    public void testGetId(){
        assertEquals("BLUE5", card.getId(), "Error in class card : method getId()");
    }
}
