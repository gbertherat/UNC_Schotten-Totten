package unc.gl.st.stock;

import unc.gl.st.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Stock {
    private Stack<Card> cardStack;

    public Stock(ArrayList<Card> cards) {
        Collections.shuffle(cards);
        this.cardStack = new Stack<>();
        cardStack.addAll(cards);
    }

    public Card draw() throws EmptyStockException {
        if ( isEmpty()){
            throw new EmptyStockException();
        }
        return cardStack.pop();
    }

    public boolean isEmpty(){
        return this.cardStack.empty();
    }

    private class EmptyStockException extends Exception{
        public EmptyStockException() {
            super("The stock is empty");
        }
    }
}
