package unc.gl.st.game;

import java.time.Instant;

public class GameOptions {
    private Instant created;
    private String name;

    public GameOptions(String name){
        this.created = Instant.now();
        this.name = name;
    }

    public Instant getCreated(){
        return this.created;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
