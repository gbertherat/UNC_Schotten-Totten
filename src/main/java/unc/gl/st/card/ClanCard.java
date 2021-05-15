package unc.gl.st.card;

import com.vaadin.flow.component.html.Image;

public class ClanCard implements Card{
    public static final int NUM_CARDS_BY_COLOR = 9;
    private int strength;
    private Color color;
    private Image image;

    public ClanCard(int strength, Color color) {
        this.strength = strength;
        this.color = color;

        Image cardImage = new Image("/img/cartes_clan/" + this.getId().toLowerCase() + ".png", this.getId());
        cardImage.setClassName("carte");
        cardImage.setVisible(false);
        this.image = cardImage;
    }

    public String getId(){
        return this.color + "-" + String.valueOf(this.strength);
    }
    
    public int getStrength(){
        return this.strength;
    }

    public Color getColor(){
        return this.color;
    }

    public Image getImage(){
        return this.image;
    }

    public void setImage(Image image){
        this.image = image;
    }
}
