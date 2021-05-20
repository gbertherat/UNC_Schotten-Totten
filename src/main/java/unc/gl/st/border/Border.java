package unc.gl.st.border;

import java.util.ArrayList;
import java.util.List;

public class Border {
    private static int NUM_STONES = 9;

    Random rand = new Random();
    File dir = new File("src/main/webapp/img/tuile_borne");
    File[] files = dir.listFiles();

    private List<Stone> stones;

    public Border(){
        this.stones = new ArrayList<>();
        for(int i = 0; i < NUM_STONES; i++){
            Image borderCardImage = null;
            if(files != null) {
                String borderCardPath = files[rand.nextInt(files.length)].getPath();
                borderCardPath = borderCardPath.replace("src\\main\\webapp", "");
                borderCardImage = new Image(borderCardPath, "Carte Frontière " + i);
                borderCardImage.setClassName("border noGap");
            }

            stones.add(new Stone(i, borderCardImage));
        }
    }

    public List<Stone> getStones(){
        return this.stones;
    }

    public int getNumStones(){
        return NUM_STONES;
    }
}
