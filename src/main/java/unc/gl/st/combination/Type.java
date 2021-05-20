package unc.gl.st.combination;

import unc.gl.st.card.Card;

import java.util.List;

public interface Type {
    boolean supports(List<Card> cards);
}
