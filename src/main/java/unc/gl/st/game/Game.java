package unc.gl.st.game;

public class Game {
    private int id;
    private GameOptions options;
    private GameStatus status;
    
    /**
     * Constructor for class Game
     * @param id : the id of the game
     * @param options : the options of the game
     */
    public Game(int id, GameOptions options){
        this.id = id;
        this.options = options;
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
}
