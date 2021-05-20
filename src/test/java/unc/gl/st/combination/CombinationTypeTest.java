package unc.gl.st.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import unc.gl.st.card.Card;
import unc.gl.st.card.ClanCard;
import unc.gl.st.card.Color;

public class CombinationTypeTest {
    private static Combination colorRun;
    private static Combination threeOfKind;
    private static Combination color;
    private static Combination run;
    private static Combination sum;

    @BeforeAll
    public static void setUp(){
        ArrayList<Card> cards = new ArrayList<>();

        cards.add(new ClanCard(1, Color.BLUE));
        cards.add(new ClanCard(2, Color.BLUE));
        cards.add(new ClanCard(3, Color.BLUE));
        colorRun = new Combination(cards);
        
        cards = new ArrayList<>();

        cards.add(new ClanCard(3, Color.BLUE));
        cards.add(new ClanCard(3, Color.RED));
        cards.add(new ClanCard(3, Color.YELLOW));
        threeOfKind = new Combination(cards);

        cards = new ArrayList<>();

        cards.add(new ClanCard(3, Color.BLUE));
        cards.add(new ClanCard(6, Color.BLUE));
        cards.add(new ClanCard(2, Color.BLUE));
        color = new Combination(cards);

        cards = new ArrayList<>();

        cards.add(new ClanCard(1, Color.BLUE));
        cards.add(new ClanCard(2, Color.YELLOW));
        cards.add(new ClanCard(3, Color.RED));
        run = new Combination(cards);

        cards = new ArrayList<>();

        cards.add(new ClanCard(4, Color.YELLOW));
        cards.add(new ClanCard(1, Color.BLUE));
        cards.add(new ClanCard(2, Color.RED));
        sum = new Combination(cards);
    }

    @Test
    public void findForTest(){
        assertEquals(CombinationType.COLOR_RUN, CombinationType.findFor(colorRun));
        assertEquals(CombinationType.THREE_OF_KIND, CombinationType.findFor(threeOfKind));
        assertEquals(CombinationType.COLOR, CombinationType.findFor(color));
        assertEquals(CombinationType.RUN, CombinationType.findFor(run));
        assertEquals(CombinationType.SUM, CombinationType.findFor(sum));
    }
}
