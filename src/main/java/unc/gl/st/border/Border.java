package unc.gl.st.border;

import java.util.List;

public class Border {
    private static int NUM_STONES = 9;

    private List<Stone> stones;

    public Border(){
        for(int i = 0; i < NUM_STONES; i++){
            stones.add(new Stone(i));
        }
    }

    public List<Stone> getStones(){
        return this.stones;
    }
}
