package unc.gl.st.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* SINGLETON CLASS */
public class GameRegistry {
    private List<Game> games;

    private GameRegistry(){
        this.games = new ArrayList<>();
    }
    private static GameRegistry instance = new GameRegistry();

    public static GameRegistry getInstance(){
        return instance;
    }

    public Game createNewGame(GameOptions options){
        Game newGame = new Game(games.size(), options);
        this.games.add(newGame);
        return newGame;
    }

    public void removeGame(int id){
        this.games.remove(id);
    }

    public Optional<Game> findGame(int id){
        return Optional.ofNullable(this.games.get(id));
    }

    public List<Game> findAllWaitingGames(){
        List<Game> waitingGames = new ArrayList<>();
        for(Game game : this.games){
            if(game.getStatus() == GameStatus.CREATED){
                waitingGames.add(game);
            }
        }
        return waitingGames;
    }
}
