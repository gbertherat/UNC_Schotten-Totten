package unc.gl.st.border;

import unc.gl.st.player.Player;

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

    public int getNbrAdjacentStones(Player player){
        int i = 0;
        int nbAdjacentOwned = 0;

        for (Stone stone : this.getStones()) {
            if (player.equals(stone.getOwnBy())) {
                i++;
                if (i > nbAdjacentOwned) {
                    nbAdjacentOwned = i;
                }
            } else {
                i = 0;
            }
        }
        return nbAdjacentOwned;

        //        while(selectedStone.getId()+i <= border.getNumStones()-1 && border.getStones().get(selectedStone.getId()+i).getOwnBy() == stoneOwner
//                || selectedStone.getId()-i >= 0 && border.getStones().get(selectedStone.getId()-i).getOwnBy() == stoneOwner){
//            if(selectedStone.getId()+i <= border.getNumStones()-1 && border.getStones().get(selectedStone.getId()+i).getOwnBy() == stoneOwner
//                    && selectedStone.getId()-i >= 0 && border.getStones().get(selectedStone.getId()-i).getOwnBy() == stoneOwner){
//                nbAdjacentOwned += 2;
//            } else {
//                nbAdjacentOwned++;
//            }
//            i++;
//        }
    }
}
