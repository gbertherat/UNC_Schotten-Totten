package unc.gl.st.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import unc.gl.st.border.Border;
import unc.gl.st.border.Stone;
import unc.gl.st.card.Card;
import unc.gl.st.exception.EmptyStockException;
import unc.gl.st.game.Game;
import unc.gl.st.game.GameHandler;
import unc.gl.st.game.GameStatus;
import unc.gl.st.player.Hand;
import unc.gl.st.player.Player;
import unc.gl.st.stock.Stock;
import unc.gl.st.stock.StockFactories;
import unc.gl.st.view.component.ClanCardImage;
import unc.gl.st.view.component.StoneImage;

import java.util.List;
import java.util.Random;

public class GameMenu {
    private static List<Player> players = null;

    private static Player activePlayer = null;
    private static Hand activePlayerHand = null;

    private static Player winningPlayer = null;

    private static Border border = null;

    private static Game game = null;
    private static GameHandler gameHandler;

    private static Card selectedCard = null;
    private static Stone selectedStone = null;
    private static final Stock stock = StockFactories.createClanStock();

    private static void gameWonBy(HorizontalLayout handLayout, HorizontalLayout scoreLayout) {
        if (winningPlayer != null) {
            System.out.println(winningPlayer.getName() + " a gagné la partie!");
            handLayout.removeAll();
            scoreLayout.removeAll();

            Label winningLabel = new Label(winningPlayer.getName() + " a gagné la partie!");
            winningLabel.setClassName("win");
            scoreLayout.add(winningLabel);
        }
    }

    private static GameHandler.ResponseCode placeCardOnStone(VerticalLayout stoneLayout) {

        GameHandler.ResponseCode responseCode = gameHandler.placeCardOnStone(activePlayer, selectedCard, selectedStone );

        switch (responseCode){
            case NO_ACTION:
                break;
            case OK:
                ClanCardImage clanCardImage = new ClanCardImage(selectedCard, true, activePlayer);
                if (activePlayer.getId() == 0) {
                    stoneLayout.addComponentAsFirst(clanCardImage);
                } else {
                    stoneLayout.add(clanCardImage);
                }
                activePlayer = game.getBoard().getOpponentPlayer(activePlayer);

                final Player stoneOwner = selectedStone.getOwnBy();

                if (stoneOwner != null){
                    stoneLayout.getChildren()
                            .map(Component::getElement)
                            .filter(stoneChildElement -> stoneOwner.getName().equals(stoneChildElement.getAttribute("added-by")))
                            .forEach(ownerCardElement -> ownerCardElement.setAttribute("class", "smallcarte winningArea"));
                }

                selectedCard = null;
                selectedStone = null;
                break;
            case GAME_FINISHED:
                winningPlayer = selectedStone.getOwnBy();
        }
        return responseCode;
    }

    private static void updateHand(HorizontalLayout handLayout) {
        handLayout.removeAll();
        activePlayerHand = activePlayer.getHand();

        try {
            activePlayerHand.refillHand(stock);
        } catch (EmptyStockException e) {
            System.out.println("La pioche est vide!");
        }

        for (Card card : activePlayerHand.getCards()) {
            ClanCardImage cardImage = new ClanCardImage(card);
            cardImage.setVisible(false);

            cardImage.addClickListener(ev -> {
                selectedCard = card;
                clearOutlines(handLayout);
                cardImage.setClassName("carte outline");
            });

            handLayout.add(cardImage);
        }

        Button showCards = new Button("Montrer les cartes de " + activePlayer.getName());
        showCards.setClassName("button");
        showCards.addClickListener(ev -> {
            handLayout.getChildren()
                    .forEach(component -> component.setVisible(true));
            handLayout.remove(showCards);
        });
        handLayout.add(showCards);
    }

    private static void updateScore(HorizontalLayout layout) {
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

    private static void clearOutlines(HorizontalLayout handLayout) {
        handLayout.getChildren()
                .map(Component::getElement)
                .filter(element -> element.getAttribute("class").contains("outline"))
                .forEach(element -> element.setAttribute("class", "carte"));
    }


    public static VerticalLayout build(Game game) {
        GameMenu.game = game;
        GameMenu.gameHandler = new GameHandler(game);
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
        activePlayer = players.get(rand.nextInt(players.size()));

        /* BORDER */
        border = game.getBoard().getBorder();
        VerticalLayout playerSidesLayout = new VerticalLayout();
        playerSidesLayout.setSizeFull();
        playerSidesLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        playerSidesLayout.add(
                new Label(players.get(0).getName()),
                new Label(players.get(1).getName())
        );
        borderLayout.add(playerSidesLayout);

        for (Stone stone : border.getStones()) {
            VerticalLayout stoneLayout = new VerticalLayout();
            stoneLayout.setClassName("noGap");
            stoneLayout.setPadding(false);
            stoneLayout.setAlignItems(Alignment.CENTER);
            stoneLayout.setJustifyContentMode(JustifyContentMode.CENTER);

            StoneImage stoneImage = new StoneImage();

            stoneImage.addClickListener(ev -> {
                selectedStone = stone;

                GameHandler.ResponseCode responseCode = placeCardOnStone(stoneLayout);
                switch (responseCode) {
                    case OK:
                        updateHand(handLayout);
                        updateScore(scoreLayout);
                        break;
                    case GAME_FINISHED:
                        gameWonBy(handLayout, scoreLayout);
                        game.setStatus(GameStatus.FINISHED);
                }
            });
            stoneLayout.add(stoneImage);

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
