package unc.gl.st.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import unc.gl.st.player.Player;

public class GameTest {
    private static Game game;

    @BeforeAll
    public static void setUp(){
        GameOptions options = new GameOptions("test");
        GameRegistry registry = GameRegistry.getInstance();
        game = registry.createNewGame(options);
        Player player = new Player("test");
        game.addPlayer(player);
    }

    @Test
    public void gameTest(){
        assertEquals(GameStatus.CREATED, game.getStatus());
        game.start();
        assertEquals(GameStatus.STARTED, game.getStatus());
    }
}
