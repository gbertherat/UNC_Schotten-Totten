package unc.gl.st.border;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import unc.gl.st.card.Card;

public class StoneArea {
    private static final int MAX_NUM_CARDS = 3;
    private final List<Card> cards;

    public StoneArea(){
        this.cards = new ArrayList<>();
    }

    public boolean isFull(){
        return this.cards.size() == MAX_NUM_CARDS;
    }
    
    public void addCard(Card card){
        this.cards.add(card);
        cards.sort(Comparator.comparingInt(Card::getStrength));
    }

    public List<Card> getCards(){
        return this.cards;
    }
}
