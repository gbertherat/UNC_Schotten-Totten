package unc.gl.st.card;

public class ClanCard implements Card{
    public static final int NUM_CARDS_BY_COLOR = 9;
    private int strength;
    private Color color;

    public ClanCard(int strength, Color color) throws Exception {
        this.strength = strength;
        this.color = color;
    }

    public String getId(){
        return this.color + String.valueOf(this.strength);
    }
    
    public int getStrength(){
        return this.strength;
    }

    public Color getColor(){
        return this.color;
    }
}
