package unc.gl.st.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import unc.gl.st.border.Border;
import unc.gl.st.border.Stone;
import unc.gl.st.card.Card;
import unc.gl.st.exception.EmptyStockException;
import unc.gl.st.exception.FullHandException;
import unc.gl.st.game.Game;
import unc.gl.st.player.Hand;
import unc.gl.st.player.Player;
import unc.gl.st.stock.Stock;
import unc.gl.st.stock.StockFactories;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameMenu{
    private static Card selectedCard = null;
    private static Stone selectedStone = null;

    public static VerticalLayout build(VerticalLayout mainLayout, Game game) {
        VerticalLayout gameLayout = new VerticalLayout();
        gameLayout.setSizeFull();
        gameLayout.setAlignItems(Alignment.CENTER);
        gameLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        game.start();

        Random rand = new Random();

        /* PLAYER */
        HorizontalLayout playerLayout = new HorizontalLayout();
        playerLayout.setSizeFull();
        playerLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        List<Player> players = game.getPlayers();
        Player activePlayer = players.get(rand.nextInt(players.size()));

        Label whoseTurnLabel = new Label("Tour de: " + activePlayer.getName());
        whoseTurnLabel.setClassName("activePlayer");
        playerLayout.add(whoseTurnLabel);
        gameLayout.add(playerLayout);

        /* BORDER */
        HorizontalLayout topBorderLayout = new HorizontalLayout();
        topBorderLayout.setSizeFull();

        HorizontalLayout borderLayout = new HorizontalLayout();

        HorizontalLayout bottomBorderLayout = new HorizontalLayout();
        bottomBorderLayout.setSizeFull();

        Border border = new Border();

        File dir = new File("src/main/webapp/img/tuile_borne");
        File[] files = dir.listFiles();

        if(files != null) {
            for (int i = 0; i < border.getNumStones(); i++) {
                String borderCardPath = files[rand.nextInt(files.length)].getPath();
                borderCardPath = borderCardPath.replace("src\\main\\webapp", "");
                Image borderCardImage = new Image(borderCardPath, "Carte Frontière " + i);
                borderCardImage.setClassName("border");

                Stone stone = border.getStones().get(i);
                borderCardImage.addClickListener(ev ->{
                    selectedStone = stone;

                    for(Iterator<Component> iterator = borderLayout.getChildren().iterator(); iterator.hasNext();){
                        Component item = iterator.next();

                        if(item.getElement().getAttribute("alt").contains(String.valueOf(selectedStone.getId()))){
                            item.getElement().setAttribute("class","border outline");
                            continue;
                        }

                        item.getElement().setAttribute("class","border");
                    }
                });

                borderLayout.add(borderCardImage);
            }
        }
        gameLayout.add(borderLayout);

        /* HAND */
        HorizontalLayout cardLayout = new HorizontalLayout();
        cardLayout.setSizeFull();

        Hand hand = activePlayer.getHand();
        Stock stock = StockFactories.createClanStock();

        for(int i = 0; i < Hand.HAND_SIZE; i++){
            try {
                Card randCard = stock.draw();
                hand.addCard(randCard);
                
                Image cardImage = new Image("/img/cartes_clan/" + randCard.getId().toLowerCase() + ".png", randCard.getId());
                cardImage.setClassName("carte");
                cardImage.setVisible(false);

                cardImage.addClickListener(ev -> {
                    selectedCard = randCard;

                    for(Iterator<Component> iterator = cardLayout.getChildren().iterator(); iterator.hasNext();){
                        Component item = iterator.next();

                        if(item.getElement().getAttribute("alt") != null && item.getElement().getAttribute("alt").equals(selectedCard.getId())){
                            item.getElement().setAttribute("class","carte outline");
                            continue;
                        }

                        item.getElement().setAttribute("class","carte");
                    }
                });

                cardLayout.add(cardImage);
            } catch (FullHandException | EmptyStockException e) {
                e.printStackTrace();
            }
        }

        Button showCards = new Button("Montrer les cartes");
        showCards.setClassName("button");
        showCards.addClickListener(ev -> {
            for (int i = 0; i < cardLayout.getComponentCount(); i++) {
                cardLayout.getComponentAt(i).setVisible(true);
                cardLayout.remove(showCards);
            }
        });
        cardLayout.add(showCards);
        gameLayout.add(cardLayout);

        /* STOCK */
        HorizontalLayout stockLayout = new HorizontalLayout();
        stockLayout.setSizeFull();
        stockLayout.setAlignItems(Alignment.END);
        stockLayout.setJustifyContentMode(JustifyContentMode.END);

        Image stockImage = new Image("/img/cartes_clan/back.png", "Carte Pioche");
        stockImage.setClassName("carte");
        stockLayout.add(stockImage);

        cardLayout.add(stockLayout);

        return gameLayout;
    }
}
