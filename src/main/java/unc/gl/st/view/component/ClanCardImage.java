package unc.gl.st.view.component;

import com.vaadin.flow.component.html.Image;
import unc.gl.st.card.Card;
import unc.gl.st.player.Player;

public class ClanCardImage extends Image {

    public ClanCardImage(Card card) {
        super("/img/cartes_clan/" + card.getId().toLowerCase() + ".png", card.getId());
        this.setClassName("carte");
    }

    public ClanCardImage(Card card, boolean isSmallCard) {
        this(card);
        if (isSmallCard){
            this.setClassName("smallcarte");
            this.getElement().setAttribute("style", "margin:-3vw;");
        }
    }

    public ClanCardImage(Card card, boolean isSmallCard, Player owner){
        this(card, isSmallCard);
        this.setAddedBy(owner);
    }

    public void setAddedBy(Player player){
        this.getElement().setAttribute("added-by", player.getName());
    }

    public void removeAddedBy(){
        this.getElement().removeAttribute("added-by");
    }
}
