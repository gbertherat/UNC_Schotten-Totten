package unc.gl.st.player;

import unc.gl.st.card.ClanCard;
import unc.gl.st.exception.FullHandException;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int HAND_SIZE = 6;
    private List<ClanCard> cards;

    public void addCard(ClanCard card) throws FullHandException {
        if (isFull()) {
            throw new FullHandException();
        } else {
            this.cards.add(card);
        }
    }

    public void removeCard(ClanCard card) {
        cards.remove(card);
    }

    public boolean isFull() {
        return cards.size() == HAND_SIZE;
    }

    public boolean contains(ClanCard card) {
        return cards.contains(card);
    }

    public Hand() {
        this.cards = new ArrayList<>();
    }
}
