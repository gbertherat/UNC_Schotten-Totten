package unc.gl.st.player;

import unc.gl.st.card.Card;
import unc.gl.st.exception.EmptyStockException;
import unc.gl.st.exception.FullHandException;
import unc.gl.st.stock.Stock;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int HAND_SIZE = 6;
    private final List<Card> cards;

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
        return this.cards.contains(card);
    }

    public void refillHand(Stock stock) throws EmptyStockException{

        int nbCardsToAdd = Hand.HAND_SIZE - this.getCards().size();
        for(int i = 0; i < nbCardsToAdd; i++) {
            try {
                Card card = stock.draw();
                this.addCard(card);
            } catch (FullHandException e)  {
                e.printStackTrace();
            }
        }
    }

    public Hand() {
        this.cards = new ArrayList<>();
    }
}
