package unc.gl.st.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import unc.gl.st.card.ClanCard;
import unc.gl.st.card.Color;

public class CombinationTest {
    private static Combination combination1;
    private static Combination combination2;
    private static Combination combination3;

    @BeforeAll
    public static void setUp() throws Exception{
        ArrayList<ClanCard> cards = new ArrayList<>();
        cards.add(new ClanCard(3, Color.BLUE));
        combination1 = new Combination(cards);

        cards.add(new ClanCard(1, Color.BLUE));
        combination2 = new Combination(cards);

        cards.add(new ClanCard(2, Color.BLUE));
        combination3 = new Combination(cards);
    }

    @Test
    public void compareToTest(){
        assertEquals(-1, combination1.compareTo(combination2));
        assertEquals(0, combination2.compareTo(combination2));
        assertEquals(1, combination3.compareTo(combination2));
    }
}
