package unc.gl.st.card;

public class ClanCard {
    protected final int NUM_CARDS_BY_COLOR = 9;
    private int strength;
    private Color color;

    public ClanCard(int strenght, Color color){
        this.strength = strenght;
        this.color = color;
    }

    public String getId(){
        return this.color.toString() + String.valueOf(this.strength);
    }
}
