package unc.gl.st.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ClanCardTest {
    private ClanCard card;

    @org.junit.Before
    public void setUp() throws Exception{
        card = new ClanCard(5, Color.BLUE);
    }

    @Test
    public void testGetId(){
        assertEquals("BLUE6", card.getId(), "Error in class card : method getId()");
    }
}
