package unc.gl.st.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GameRegistryTest {
    private static GameRegistry registry;

    @BeforeAll
    public static void setUp(){
        registry = GameRegistry.getInstance();

        GameOptions options = new GameOptions("test");

        for(int i = 0; i < 5; i++){
            registry.createNewGame(options);
        }
        registry.findGame(3)
                .get()
                .setStatus(GameStatus.STARTED);
    }

    @Test
    public void createNewGameTest(){
        int size = registry.findAllWaitingGames().size();
        registry.createNewGame(new GameOptions("test"));
        assertEquals(size+1, registry.findAllWaitingGames().size());
        registry.removeGame(size);
    }

    @Test
    public void findAllWaitingGamesTest(){
        assertEquals(4, registry.findAllWaitingGames().size());
    }

    
}
