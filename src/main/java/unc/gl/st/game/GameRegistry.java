package unc.gl.st.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* SINGLETON CLASS */
public class GameRegistry {
    private final List<Game> games;

    /**
     * Private constructor for class GameRegistry
     * Only one instance of the class is allowed
     */
    private GameRegistry() {
        this.games = new ArrayList<>();
    }

    private static final GameRegistry instance = new GameRegistry();

    /**
     * Get the unique instance of the class
     *
     * @return a GameRegistry object
     */
    public static GameRegistry getInstance() {
        return instance;
    }

    /**
     * Allows for the creation of a new game
     *
     * @param options : the options of the game
     * @return a game object
     */
    public Game createNewGame(GameOptions options) {
        Game newGame = new Game(games.size(), options); // We created the game object
        this.games.add(newGame); // We add the game object to the list of games
        return newGame;
    }

    /**
     * Get a game from the list of games
     *
     * @param id : the id of the game to get
     * @return a game object or an empty Optional object
     */
    public Optional<Game> findGame(int id) {
        return Optional.ofNullable(this.games.get(id));
    }

    /**
     * Remove a game object from the list of games
     *
     * @param id : the id of the game to remove
     */
    public void removeGame(int id) {
        this.games.remove(id);
    }


    /**
     * Get all awaiting games
     *
     * @return a list of games
     */
    public List<Game> findAllWaitingGames() {
        return this.games.stream().filter(game -> game.getStatus() == GameStatus.CREATED).collect(Collectors.toList());
    }
}
