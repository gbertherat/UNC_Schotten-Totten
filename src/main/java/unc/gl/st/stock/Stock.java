package unc.gl.st.stock;

import unc.gl.st.card.Card;
import unc.gl.st.exception.EmptyStockException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Stock {
    private Stack<Card> cardStack;

    public Stock(ArrayList<Card> cards) {
        Collections.shuffle(cards);
        this.cardStack = new Stack<>();
        cardStack.addAll(cards);
    }

    public Card draw() throws EmptyStockException {
        if (isEmpty()){
            throw new EmptyStockException();
        }
        return cardStack.pop();
    }

    public Stack<Card> getCards(){
        return this.cardStack;
    }

    public void removeTopCard(){
        this.cardStack.pop();
    }

    public boolean isEmpty(){
        return this.cardStack.empty();
    }
}
