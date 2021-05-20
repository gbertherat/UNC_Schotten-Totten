package unc.gl.st.border;

import java.util.ArrayList;
import java.util.List;

public class Border {
    private static final int NUM_STONES = 9;
    private final List<Stone> stones;

    public Border(){
        this.stones = new ArrayList<>();
        for(int i = 0; i < NUM_STONES; i++){
            stones.add(new Stone(i));
        }
    }

    public List<Stone> getStones(){
        return this.stones;
    }

    public int getNumStones(){
        return NUM_STONES;
    }
}
