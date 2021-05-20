package unc.gl.st.game;

import java.util.List;

import unc.gl.st.board.Board;
import unc.gl.st.player.Player;

public class Game {
    private final int id;
    private GameOptions options;
    private final Board board;
    private GameStatus status;
    
    /**
     * Constructor for class Game
     * @param id : the id of the game
     * @param options : the options of the game
     */
    public Game(int id, Board board, GameOptions options){
        this.id = id;
        this.options = options;
        this.board = board;
        this.status = GameStatus.CREATED;
    }

    /**
     * Get the id of a game object
     * @return the id of the game
     */
    public int getId(){
        return this.id;
    }

    /**
     * Get the options of a game object
     * @return the options of the game
     */
    public GameOptions getOptions(){
        return this.options;
    }

    /**
     * Set the options of a game object
     * @param options : the options to set
     */
    public void setOptions(GameOptions options){
        this.options = options;
    }

    /**
     * Get the board of a game object
     * @return the board of the game
     */
    public Board getBoard(){
        return this.board;
    }

    /**
     * Get the status of a game object
     * @return the status of the game
     */
    public GameStatus getStatus(){
        return this.status;
    }

    /**
     * Set the status of a game object
     * @param status : the status to set
     */
    public void setStatus(GameStatus status){
        this.status = status;
    }

    /**
     * Allows the start of a game
     */
    public void start(){
        if(this.status == GameStatus.CREATED){
            this.status = GameStatus.STARTED;
        }
    }

    /**
     * Get the boolean value of "is the board full?"
     * @return bool of "is the the board full?"
     */
    public boolean isFull(){
        return this.board.isFull();
    }

    /**
     * Get the players of a game
     * @return the players of a game
     */
    public List<Player> getPlayers(){
        return this.board.getPlayers();
    }

    /**
     * Add a player to a game
     * @param player The player to add
     */
    public void addPlayer(Player player){
        this.board.addPlayer(player);
    }

    /**
     * Remove a player from a game
     * @param player The player to remove
     */
    public void removePlayer(Player player){
        this.board.removePlayer(player);
    }

    /**
     * Get opponent of a player
     * @param player The player to get the opponent of
     * @return A player
     */
    public Player getOpponent(Player player){
        return this.board.getOpponentPlayer(player);
    }
}
