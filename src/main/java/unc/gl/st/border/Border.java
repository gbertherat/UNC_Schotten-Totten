package unc.gl.st.border;

import com.vaadin.flow.component.html.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
