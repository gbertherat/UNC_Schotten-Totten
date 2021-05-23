package unc.gl.st.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import unc.gl.st.card.Card;
import unc.gl.st.player.Hand;
import unc.gl.st.player.Player;

public class HandLayout extends HorizontalLayout {

    Card selectedCard;

    public HandLayout() {
        super();
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void updateForPlayer(Player player) {
        this.removeAll();
        Hand hand = player.getHand();
        for (Card card : hand.getCards()) {
            ClanCardImage cardImage = new ClanCardImage(card);
            cardImage.setVisible(false);

            cardImage.addClickListener(ev -> {
                selectedCard = card;
                clearOutlines(this);
                cardImage.setClassName("carte outline");
            });

            this.add(cardImage);
        }

        Button showCards = new Button("Montrer les cartes de " + player.getName());
        showCards.setClassName("button");
        showCards.addClickListener(ev -> {
            this.getChildren()
                    .forEach(component -> component.setVisible(true));
            this.remove(showCards);
        });
        this.add(showCards);
    }

    private void clearOutlines(HandLayout handLayout) {
        handLayout.getChildren()
                .map(Component::getElement)
                .filter(element -> element.getAttribute("class").contains("outline"))
                .forEach(element -> element.setAttribute("class", "carte"));
    }
}
