package unc.gl.st.border;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import unc.gl.st.card.ClanCard;
import unc.gl.st.card.Color;
import unc.gl.st.player.Player;

public class StoneTest {
    private static Player player1;
    private static Player player2;
    private static Stone stone1;
    private static Stone stone2;
    private static Stone stone3;

    @BeforeAll
    private static void setUp(){
        player1 = new Player("A");
        player2 = new Player("B");
        stone1 = new Stone(0);
        stone2 = new Stone(1);
        stone3 = new Stone(2);
    }

    @Test
    public void addCardForTest(){
        // STONE 1
        stone1.addCardFor(player1, new ClanCard(3, Color.BLUE));
        stone1.addCardFor(player1, new ClanCard(4, Color.BLUE));
        stone1.addCardFor(player1, new ClanCard(5, Color.BLUE));

        stone1.addCardFor(player2, new ClanCard(2, Color.RED));
        stone1.addCardFor(player2, new ClanCard(3, Color.RED));
        stone1.addCardFor(player2, new ClanCard(4, Color.RED));
        
        assertEquals(player1, stone1.getOwnBy());

        // STONE 2
        stone2.addCardFor(player1, new ClanCard(1, Color.BLUE));
        stone2.addCardFor(player1, new ClanCard(2, Color.BLUE));
        stone2.addCardFor(player1, new ClanCard(3, Color.BLUE));

        stone2.addCardFor(player2, new ClanCard(2, Color.RED));
        stone2.addCardFor(player2, new ClanCard(3, Color.RED));
        stone2.addCardFor(player2, new ClanCard(4, Color.RED));

        assertEquals(player2, stone2.getOwnBy());

        // STONE 3
        stone3.addCardFor(player1, new ClanCard(1, Color.BLUE));
        stone3.addCardFor(player1, new ClanCard(2, Color.BLUE));
        stone3.addCardFor(player1, new ClanCard(3, Color.BLUE));

        stone3.addCardFor(player2, new ClanCard(1, Color.RED));
        stone3.addCardFor(player2, new ClanCard(2, Color.RED));
        stone3.addCardFor(player2, new ClanCard(3, Color.RED));

        assertEquals(player1, stone3.getOwnBy());
    }
}
