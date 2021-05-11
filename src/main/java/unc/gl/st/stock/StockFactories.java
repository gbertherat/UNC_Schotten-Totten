package unc.gl.st.stock;

import unc.gl.st.card.Card;
import unc.gl.st.card.ClanCard;
import unc.gl.st.card.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class StockFactories {

    public static Stock createClanStock() {
        ArrayList<Card> cards = new ArrayList<>();

        Arrays.stream(Color.values())
                .forEach(color ->
                        IntStream.rangeClosed(1, ClanCard.NUM_CARDS_BY_COLOR)
                                .forEach(num -> cards.add(new ClanCard(num, color)))
                );

        return new Stock(cards);
    }

}
