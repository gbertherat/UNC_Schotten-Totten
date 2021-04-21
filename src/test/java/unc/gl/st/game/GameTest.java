package unc.gl.st.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import unc.gl.st.player.Player;

public class GameTest {
    private static Game game;
    private static Player player;

    @BeforeAll
    public static void setUp(){
        GameOptions options = new GameOptions("test");
        GameRegistry registry = GameRegistry.getInstance();
        game = registry.createNewGame(options);
        player = new Player("test");
        game.addPlayer(player);
    }

    @Test
    public void gameTest(){
        assertEquals(GameStatus.CREATED, game.getStatus());
        game.start(player);
        assertEquals(GameStatus.STARTED, game.getStatus());
    }
}
