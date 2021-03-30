package unc.gl.st.board;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unc.gl.st.player.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BoardTest {
    private static Board board;
    private static Player player1;
    private static Player player2;

    @BeforeAll
    public static void setUp() {
        player1 = new Player("P1");
        player2 = new Player("P2");
    }

    @BeforeEach
    public void setUpEach() {
        board = new Board();
        board.addPlayer(player1);
        board.addPlayer(player2);
    }

    @Test
    public void testAddPlayer() {
        ArrayList<Player> list = new ArrayList<>();
        list.add(player1);
        list.add(player2);
        assertEquals(list, board.getPlayers());
        board.addPlayer(player2);
        assertEquals(list, board.getPlayers());
    }

    @Test
    public void testGetOpponentPlayer() {
        assertEquals(board.getOpponentPlayer(player1), player2);
        assertNotEquals(board.getOpponentPlayer(player1), player1);
        assertEquals(board.getOpponentPlayer(player2), player1);
    }

    @Test
    public void testRemovePlayer() {
        ArrayList<Player> list = new ArrayList<>();
        list.add(player1);
        board.removePlayer(player2);
        assertEquals(list, board.getPlayers());
    }


}
