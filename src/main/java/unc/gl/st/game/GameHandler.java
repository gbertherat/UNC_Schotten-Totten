package unc.gl.st.game;

import unc.gl.st.border.Stone;
import unc.gl.st.card.Card;
import unc.gl.st.exception.EmptyStockException;
import unc.gl.st.player.Hand;
import unc.gl.st.player.Player;
import unc.gl.st.stock.Stock;
import unc.gl.st.stock.StockFactories;

import java.util.List;
import java.util.Random;

public class GameHandler {

    public enum ResponseCode {
        NO_ACTION,
        OK,
        ERROR,
        GAME_FINISHED
    }

    final Game game;
    Player activePlayer;
    Player winner;

    final Stock stock = StockFactories.createClanStock();

    public GameHandler(Game game) {
        this.game = game;

        Random rand = new Random();
        List<Player> players = game.getPlayers();
        activePlayer = players.get(rand.nextInt(players.size()));
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Game getGame() {
        return game;
    }

    public Player getWinner() {
        return winner;
    }

    public Stock getStock() {
        return stock;
    }

    public ResponseCode placeCardOnStone(Card card, Stone stone) {
        if (card == null || stone == null || stone.isFullFor(activePlayer)) {
            return ResponseCode.NO_ACTION;
        }

        stone.addCardFor(activePlayer, card);
        activePlayer.getHand().removeCard(card);

        final Player stoneOwner = stone.getOwnBy();
        if (stoneOwner == null) {
            activePlayer = game.getBoard().getOpponentPlayer(activePlayer);
            return ResponseCode.OK;
        }

        stoneOwner.setScore(stoneOwner.getScore() + 1);

        int nbAdjacentOwned = game.getBoard().getBorder().getNbrAdjacentStones(stoneOwner);

        if (nbAdjacentOwned >= 3 || stoneOwner.getScore() >= 5) {
            winner = stoneOwner;
            return ResponseCode.GAME_FINISHED;
        } else {
            activePlayer = game.getBoard().getOpponentPlayer(activePlayer);
            return ResponseCode.OK;
        }
    }

    public ResponseCode updateHand(){
        final Hand activePlayerHand = activePlayer.getHand();
        if (activePlayerHand.isFull()){
            return ResponseCode.NO_ACTION;
        }
        try {
            activePlayerHand.refillHand(stock);
        } catch (EmptyStockException e) {
            System.out.println("La pioche est vide!");
        }
        return ResponseCode.OK;
    }

}
