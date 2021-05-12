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
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameMenu{
    private static List<Player> players = null;

    private static Player activePlayer = null;
    private static Hand activePlayerHand = null;

    private static Card selectedCard = null;
    private static Stone selectedStone = null;
    private static final Stock stock = StockFactories.createClanStock();

    private static boolean placeCardOnStone(VerticalLayout mainLayout, VerticalLayout stoneLayout){
        if(selectedCard != null && selectedStone != null){
            selectedStone.addCardFor(activePlayer, selectedCard);
            activePlayerHand.removeCard(selectedCard);

            Image cardImage = new Image("/img/cartes_clan/" + selectedCard.getId().toLowerCase() + ".png", selectedCard.getId());
            cardImage.setClassName("smallcarte rotate");
            cardImage.getElement().setAttribute("style","margin:-3vw;");

            if(players.size() > activePlayer.getId()+1){
                activePlayer = players.get(activePlayer.getId()+1);
                stoneLayout.add(cardImage);
            } else {
                activePlayer = players.get(0);
                stoneLayout.addComponentAsFirst(cardImage);
            }

            selectedCard = null;
            selectedStone = null;

            clearOutlines(mainLayout);

            return true;
        }

        return false;
    }

    private static void updateActivePlayer(HorizontalLayout playerLayout){
        playerLayout.removeAll();
        Label whoseTurnLabel = new Label("Tour de: " + activePlayer.getName());
        whoseTurnLabel.setClassName("activePlayer");
        playerLayout.add(whoseTurnLabel);
    }

    private static void updateHand(HorizontalLayout cardLayout){
        cardLayout.removeAll();
        activePlayerHand = activePlayer.getHand();

        int nbCardsToAdd = Hand.HAND_SIZE - activePlayerHand.getCards().size();
        for(int i = 0; i < nbCardsToAdd; i++){
            try {
                Card randCard = stock.draw();
                activePlayerHand.addCard(randCard);
            } catch (FullHandException | EmptyStockException e) {
                e.printStackTrace();
            }
        }

        for(Card card : activePlayerHand.getCards()){
            Image cardImage = new Image("/img/cartes_clan/" + card.getId().toLowerCase() + ".png", card.getId());
            cardImage.setClassName("carte");
            cardImage.setVisible(false);

            cardImage.addClickListener(ev -> {
                selectedCard = card;
                addOutline(cardLayout, cardImage, "carte");
            });

            cardLayout.add(cardImage);
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
    }

    private static void addOutline(Component layout, Image image, String name){
        for(Iterator<Component> iterator = layout.getChildren().iterator(); iterator.hasNext();){
            Component item = iterator.next();
            item.getElement().setAttribute("class",name);
        }
        image.setClassName(name + " outline");
    }

    private static void clearOutlines(VerticalLayout gameLayout){
        for(Iterator<Component> mainIte = gameLayout.getChildren().iterator(); mainIte.hasNext();){
            Component layout = mainIte.next();

            if(layout.getClass().equals(HorizontalLayout.class) || layout.getClass().equals(VerticalLayout.class)){
                for(Iterator<Component> subIte = layout.getChildren().iterator(); subIte.hasNext();) {
                    Component item = subIte.next();
                    if(item.getElement().getAttribute("class") != null && item.getElement().getAttribute("class").contains("outline")) {
                        String classNames = item.getElement().getAttribute("class");
                        classNames = classNames.replace("outline", "");
                        item.getElement().setAttribute("class", classNames);
                    }
                }
            }
        }
    }

    public static VerticalLayout build(Game game) {
        /* LAYOUTS */
        VerticalLayout gameLayout = new VerticalLayout();
        gameLayout.setSizeFull();
        gameLayout.setAlignItems(Alignment.CENTER);
        gameLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        HorizontalLayout playerLayout = new HorizontalLayout();
        playerLayout.setSizeFull();
        playerLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        HorizontalLayout borderLayout = new HorizontalLayout();

        HorizontalLayout cardLayout = new HorizontalLayout();

        Random rand = new Random();

        game.start();

        /* PLAYER */
        players = game.getPlayers();
        for(int i = 0; i < players.size(); i++){
            players.get(i).setId(i);
        }
        activePlayer = players.get(rand.nextInt(players.size()));
        updateActivePlayer(playerLayout);
        gameLayout.add(playerLayout);

        /* BORDER */
        Border border = new Border();

        File dir = new File("src/main/webapp/img/tuile_borne");
        File[] files = dir.listFiles();

        if(files != null) {
            for (int i = 0; i < border.getNumStones(); i++) {
                VerticalLayout stoneLayout = new VerticalLayout();
                stoneLayout.setClassName("noGap");
                stoneLayout.setPadding(false);
                stoneLayout.setAlignItems(Alignment.CENTER);
                stoneLayout.setJustifyContentMode(JustifyContentMode.CENTER);

                String borderCardPath = files[rand.nextInt(files.length)].getPath();
                borderCardPath = borderCardPath.replace("src\\main\\webapp", "");
                Image borderCardImage = new Image(borderCardPath, "Carte Frontière " + i);
                borderCardImage.setClassName("border noGap");

                Stone stone = border.getStones().get(i);
                borderCardImage.addClickListener(ev -> {
                    selectedStone = stone;
                    if(placeCardOnStone(gameLayout, stoneLayout)){
                        updateHand(cardLayout);
                        updateActivePlayer(playerLayout);
                    }
                });
                stoneLayout.add(borderCardImage);
                borderLayout.add(stoneLayout);
            }
        }

        gameLayout.add(borderLayout);

        /* HAND */
        updateHand(cardLayout);
        gameLayout.add(cardLayout);

        return gameLayout;
    }
}
