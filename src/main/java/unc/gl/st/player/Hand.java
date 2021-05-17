package unc.gl.st.player;

import unc.gl.st.card.Card;
import unc.gl.st.exception.FullHandException;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int HAND_SIZE = 6;
    private List<Card> cards;

    public List<Card> getCards(){
        return this.cards;
    }

    public void addCard(Card card) throws FullHandException {
        if (isFull()) {
            throw new FullHandException();
        } else {
            this.cards.add(card);
        }
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public boolean isFull() {
        return cards.size() == HAND_SIZE;
    }

    public boolean contains(Card card) {
        for(Card cards: this.cards){
            if(card.getId().equals(cards.getId())){
                return true;
            }
        }
        return false;
    }

    public Hand() {
        this.cards = new ArrayList<>();
    }
}
