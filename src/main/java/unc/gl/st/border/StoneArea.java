package unc.gl.st.border;

import java.util.ArrayList;
import java.util.List;

import unc.gl.st.card.ClanCard;

public class StoneArea {
    private static int MAX_NUM_CARDS = 3;
    private List<ClanCard> cards;

    public StoneArea(){
        this.cards = new ArrayList<ClanCard>();
    }

    public boolean isFull(){
        return this.cards.size() == MAX_NUM_CARDS;
    }
    
    public void addCard(ClanCard card){
        this.cards.add(card);
    }

    public List<ClanCard> getCards(){
        return this.cards;
    }
}
