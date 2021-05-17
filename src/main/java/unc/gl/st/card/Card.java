package unc.gl.st.card;

import com.vaadin.flow.component.html.Image;

public interface Card {
    String getId();
    int getStrength();
    Color getColor();
    Image getImage();
    void setImage(Image image);
}
