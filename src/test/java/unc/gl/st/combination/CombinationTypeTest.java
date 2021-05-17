package unc.gl.st.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import unc.gl.st.card.Card;
import unc.gl.st.card.ClanCard;
import unc.gl.st.card.Color;
import unc.gl.st.combination.CombinationType.Type;

public class CombinationTypeTest {
    private static Combination colorRun;
    private static Combination threeOfKind;
    private static Combination color;
    private static Combination run;
    private static Combination sum;

    @BeforeAll
    public static void setUp() throws Exception{
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
        cards.add(new ClanCard(1, Color.BLUE));
        cards.add(new ClanCard(2, Color.BLUE));
        color = new Combination(cards);

        cards = new ArrayList<>();

        cards.add(new ClanCard(1, Color.BLUE));
        cards.add(new ClanCard(2, Color.YELLOW));
        cards.add(new ClanCard(3, Color.RED));
        run = new Combination(cards);

        cards = new ArrayList<>();

        cards.add(new ClanCard(3, Color.YELLOW));
        cards.add(new ClanCard(1, Color.BLUE));
        cards.add(new ClanCard(2, Color.RED));
        sum = new Combination(cards);
    }

    @Test
    public void findForTest(){
        assertEquals(Type.COLOR_RUN, CombinationType.findFor(colorRun));
        assertEquals(Type.THREE_OF_KIND, CombinationType.findFor(threeOfKind));
        assertEquals(Type.COLOR, CombinationType.findFor(color));
        assertEquals(Type.RUN, CombinationType.findFor(run));
        assertEquals(Type.SUM, CombinationType.findFor(sum));
    }
}
