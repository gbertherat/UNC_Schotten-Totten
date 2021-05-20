package unc.gl.st.card;

public class ClanCard implements Card{
    public static final int NUM_CARDS_BY_COLOR = 9;
    private final int strength;
    private final Color color;

    public ClanCard(int strength, Color color) {
        this.strength = strength;
        this.color = color;
    }

    public String getId(){
        return this.color + "-" + this.strength;
    }
    
    public int getStrength(){
        return this.strength;
    }

    public Color getColor(){
        return this.color;
    }
}
