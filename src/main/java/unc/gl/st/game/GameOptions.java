package unc.gl.st.game;

import java.time.Instant;

public class GameOptions {
    private Instant created;
    private String name;

    /**
     * Constructor for class GameOptions
     * @param name : name to give to the game
     */
    public GameOptions(String name){
        this.created = Instant.now();
        this.name = name;
    }

    /**
     * Get the time at which the game was created
     * @return an Instant object
     */
    public Instant getCreated(){
        return this.created;
    }

    /**
     * Get the name of a GameOptions object
     * @return the name of the game
     */
    public String getName(){
        return this.name;
    }

    /**
     * Set the name of a GameOptions object
     * @param name : the name to set
     */
    public void setName(String name){
        this.name = name;
    }
}
