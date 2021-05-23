package unc.gl.st.card;

public interface Card {
    String getId();

    int getStrength();

    Color getColor();

    default boolean equals(Card card) {
        return this.getStrength() == card.getStrength() && this.getColor() == card.getColor();
    }
}
