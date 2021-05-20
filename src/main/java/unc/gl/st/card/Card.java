package unc.gl.st.card;

public interface Card {
    String getId();
    int getStrength();
    Color getColor();
    Image getImage();
    void setImage(Image image);
}
