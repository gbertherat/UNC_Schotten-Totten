package unc.gl.st.card;

public class ClanCard {
    public final int NUM_CARDS_BY_COLOR = 9;
    private int strength;
    private Color color;

    public ClanCard(int strength, Color color) throws Exception {
        if(strength > 0 && strength <= NUM_CARDS_BY_COLOR){
            this.strength = strength;
        } else {
            throw new Exception("Invalid card strength");
        }
        this.color = color;
    }

    public String getId(){
        return this.color.toString() + String.valueOf(this.strength);
    }

    public int getStrength(){
        return this.strength;
    }

    public void setStrength(int strength){
        this.strength = strength;
    }

    public Color getColor(){
        return this.color;
    }

    public void setColor(Color color){
        this.color = color;
    }
}
