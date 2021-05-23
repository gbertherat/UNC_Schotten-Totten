package unc.gl.st.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import unc.gl.st.border.Stone;
import unc.gl.st.card.Card;
import unc.gl.st.game.Game;
import unc.gl.st.game.GameHandler;
import unc.gl.st.player.Player;
import unc.gl.st.view.component.BorderLayout;
import unc.gl.st.view.component.HandLayout;

import java.util.List;

public class GameMenu {

    private static Game game = null;
    private static GameHandler gameHandler;

    private static HandLayout handLayout;

    private static void gameWonBy(HorizontalLayout handLayout, HorizontalLayout scoreLayout) {
        Player winner = gameHandler.getWinner();
        if (winner != null) {
            System.out.println(winner.getName() + " a gagné la partie!");
            handLayout.removeAll();
            scoreLayout.removeAll();

            Label winningLabel = new Label(winner.getName() + " a gagné la partie!");
            winningLabel.setClassName("win");
            scoreLayout.add(winningLabel);
        }
    }

    private static GameHandler.ResponseCode placeCardOnStone(Stone selectedStone, BorderLayout.StoneLayout stoneLayout) {
        Card selectedCard = handLayout.getSelectedCard();
        GameHandler.ResponseCode responseCode = gameHandler.placeCardOnStone(selectedCard, selectedStone);

        if (responseCode == GameHandler.ResponseCode.NO_ACTION) {
            return responseCode;
        }

        stoneLayout.update();

        return responseCode;
    }

    private static void updateHand(HandLayout handLayout) {
        GameHandler.ResponseCode responseCode = gameHandler.updateHand();
        if (responseCode == GameHandler.ResponseCode.NO_ACTION) {
            return;
        }

        final Player activePlayer = gameHandler.getActivePlayer();
        handLayout.updateForPlayer(activePlayer);
    }

    private static void updateScore(HorizontalLayout layout) {
        layout.removeAll();

        Label scores = new Label("Scores:");
        scores.setClassName("score");

        List<Player> players = gameHandler.getGame().getPlayers();
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

    public static VerticalLayout build(Game game) {
        GameMenu.game = game;
        game.start();
        gameHandler = game.getGameHandler();
        /* LAYOUTS */
        VerticalLayout gameLayout = new VerticalLayout();
        gameLayout.setSizeFull();
        gameLayout.setAlignItems(Alignment.CENTER);
        gameLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        HorizontalLayout playerLayout = new HorizontalLayout();
        playerLayout.setSizeFull();
        playerLayout.setJustifyContentMode(JustifyContentMode.CENTER);


        handLayout = new HandLayout();

        HorizontalLayout scoreLayout = new HorizontalLayout();

        BorderLayout borderLayout = new BorderLayout(game);
        borderLayout.getStoneLayouts()
                .forEach(stoneLayout -> stoneLayout.addClickListenerOnStoneImage(ev -> {
                    Stone selectedStone = stoneLayout.getStone();
                    GameHandler.ResponseCode responseCode = placeCardOnStone(selectedStone, stoneLayout);
                    switch (responseCode) {
                        case OK:
                            updateHand(handLayout);
                            updateScore(scoreLayout);
                            break;
                        case GAME_FINISHED:
                            gameWonBy(handLayout, scoreLayout);
                    }
                }));
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
