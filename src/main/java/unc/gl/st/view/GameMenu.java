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
import unc.gl.st.game.GameStatus;
import unc.gl.st.player.Hand;
import unc.gl.st.player.Player;
import unc.gl.st.stock.Stock;
import unc.gl.st.stock.StockFactories;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameMenu{
    private static List<Player> players = null;

    private static Player activePlayer = null;
    private static Hand activePlayerHand = null;

    private static Player winningPlayer = null;

    private static Border border = null;

    private static Card selectedCard = null;
    private static Stone selectedStone = null;
    private static final Stock stock = StockFactories.createClanStock();

    private static void gameWonBy(HorizontalLayout handLayout, HorizontalLayout scoreLayout){
        if(winningPlayer != null){
            System.out.println(winningPlayer.getName() + " a gagné la partie!");
            handLayout.removeAll();
            scoreLayout.removeAll();

            Label winningLabel = new Label(winningPlayer.getName() + " a gagné la partie!");
            winningLabel.setClassName("win");
            scoreLayout.add(winningLabel);
        }
    }

    private static int placeCardOnStone(VerticalLayout stoneLayout){
        if(selectedCard != null && selectedStone != null && !selectedStone.isFullFor(activePlayer)){
            selectedStone.addCardFor(activePlayer, selectedCard);
            activePlayerHand.removeCard(selectedCard);

            Image image = new Image(selectedCard.getImage().getSrc(), selectedCard.getId());
            image.setClassName("smallcarte");
            image.getElement().setAttribute("style","margin:-3vw;");
            if(players.size() > activePlayer.getId()+1){
                activePlayer = players.get(activePlayer.getId()+1);
                stoneLayout.addComponentAsFirst(image);
            } else {
                activePlayer = players.get(0);
                stoneLayout.add(image);
            }

            if(selectedStone.getOwnBy() != null){
                for(Iterator<Component> ite = stoneLayout.getChildren().iterator(); ite.hasNext();) {
                    Component item = ite.next();
                    for(Card card : selectedStone.getAreas().get(selectedStone.getOwnBy()).getCards()){
                        if(card.getImage().getElement().getAttribute("alt").equals(item.getElement().getAttribute("alt"))){
                            item.getElement().setAttribute("class","smallcarte winningArea");
                        }
                    }
                }

                Player ownByPlayer = selectedStone.getOwnBy();
                ownByPlayer.setScore(ownByPlayer.getScore()+1);

                int i = 1;
                int nbAdjacentOwned = 1;
                while(selectedStone.getId()+i <= border.getNumStones()-1 && border.getStones().get(selectedStone.getId()+i).getOwnBy() == ownByPlayer
                        || selectedStone.getId()-i >= 0 && border.getStones().get(selectedStone.getId()-i).getOwnBy() == ownByPlayer){
                    nbAdjacentOwned++;
                    i++;
                }

                if(nbAdjacentOwned >= 3 || ownByPlayer.getScore() >= 5){
                    winningPlayer = ownByPlayer;
                    return 2;
                }
            }

            selectedCard = null;
            selectedStone = null;

            clearOutlines();

            return 1;
        }

        return 0;
    }

    private static void updateHand(HorizontalLayout cardLayout){
        cardLayout.removeAll();
        activePlayerHand = activePlayer.getHand();

        int nbCardsToAdd = Hand.HAND_SIZE - activePlayerHand.getCards().size();
        for(int i = 0; i < nbCardsToAdd; i++){
            try {
                if (!stock.isEmpty()) {
                    Card randCard = stock.draw();
                    activePlayerHand.addCard(randCard);
                }
            } catch (FullHandException | EmptyStockException e) {
                e.printStackTrace();
            }
        }

        for(Card card : activePlayerHand.getCards()){
            Image cardImage = card.getImage();
            cardImage.setClassName("carte");
            cardImage.setVisible(false);

            cardImage.addClickListener(ev -> {
                selectedCard = card;
                clearOutlines();
                selectedCard.getImage().setClassName("carte outline");
            });

            cardLayout.add(cardImage);
        }

        Button showCards = new Button("Montrer les cartes de " + activePlayer.getName());
        showCards.setClassName("button");
        showCards.addClickListener(ev -> {
            for (int i = 0; i < cardLayout.getComponentCount(); i++) {
                cardLayout.getComponentAt(i).setVisible(true);
                cardLayout.remove(showCards);
            }
        });
        cardLayout.add(showCards);
    }

    private static void updateScore(HorizontalLayout layout){
        layout.removeAll();

        Label scores = new Label("Scores:");
        scores.setClassName("score");

        Player player1 = players.get(0);
        Label player1Score = new Label(player1.getName() + ": " + player1.getScore());
        player1Score.setClassName("score");

        Player player2 = players.get(1);
        Label player2Score = new Label(player2.getName() + ": " + player2.getScore());
        player2Score.setClassName("score");

        layout.add(scores);
        layout.add(player1Score);
        layout.add(player2Score);
    }

    private static void clearOutlines(){
        for(Card card : activePlayer.getHand().getCards()){
            card.getImage().setClassName("carte");
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

        HorizontalLayout handLayout = new HorizontalLayout();

        HorizontalLayout scoreLayout = new HorizontalLayout();

        Random rand = new Random();

        game.start();

        /* PLAYER */
        players = game.getPlayers();
        for(int i = 0; i < players.size(); i++){
            players.get(i).setId(i);
        }
        activePlayer = players.get(rand.nextInt(players.size()));

        /* BORDER */
        border = new Border();
        VerticalLayout playerSidesLayout = new VerticalLayout();
        playerSidesLayout.setSizeFull();
        playerSidesLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        playerSidesLayout.add(
                new Label(players.get(0).getName()),
                new Label(players.get(1).getName())
        );
        borderLayout.add(playerSidesLayout);

        for(Stone stone: border.getStones()) {
            VerticalLayout stoneLayout = new VerticalLayout();
            stoneLayout.setClassName("noGap");
            stoneLayout.setPadding(false);
            stoneLayout.setAlignItems(Alignment.CENTER);
            stoneLayout.setJustifyContentMode(JustifyContentMode.CENTER);

            Image borderCardImage = stone.getImage();

            borderCardImage.addClickListener(ev -> {
                selectedStone = stone;

                int result = placeCardOnStone(stoneLayout);
                if(result == 1){
                    updateHand(handLayout);
                    updateScore(scoreLayout);
                } else if(result == 2){
                    gameWonBy(handLayout, scoreLayout);
                    game.setStatus(GameStatus.FINISHED);
                }
            });
            stoneLayout.add(borderCardImage);
            borderLayout.add(stoneLayout);
        }

        gameLayout.add(borderLayout);

        /* HAND */
        updateHand(handLayout);
        gameLayout.add(handLayout);

        /* SCORE */
        updateScore(scoreLayout);
        gameLayout.add(scoreLayout);

        return gameLayout;
    }
}
