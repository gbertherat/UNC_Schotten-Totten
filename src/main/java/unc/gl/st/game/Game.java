package unc.gl.st.game;

public class Game {
    private int id;
    private GameOptions options;
    private GameStatus status;
    
    public Game(int id, GameOptions options){
        this.id = id;
        this.options = options;
        this.status = GameStatus.CREATED;
    }

    public int getId(){
        return this.id;
    }

    public GameOptions getOptions(){
        return this.options;
    }

    public void setOptions(GameOptions options){
        this.options = options;
    }

    public GameStatus getStatus(){
        return this.status;
    }

    public void setStatus(GameStatus status){
        this.status = status;
    }
}
