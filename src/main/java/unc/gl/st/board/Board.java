package unc.gl.st.board;

import unc.gl.st.border.Border;
import unc.gl.st.player.Player;
import unc.gl.st.stock.Stock;
import unc.gl.st.stock.StockFactories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    public static final int NUM_PLAYERS = 2;
    private final List<Player> players;
    private final Border border;
    private final Stock stock;

    public Board() {
        players = new ArrayList<>();
        border = new Border();
        stock = StockFactories.createClanStock();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        if (!isFull()) {
            players.add(player);
        }
    }

    public Player getOpponentPlayer(Player player) {
        return players.stream()
                .filter(curPlayer -> curPlayer != player)
                .collect(Collectors.toList())
                .get(0);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public boolean isFull() {
        return players.size() >= NUM_PLAYERS;
    }

    public Border getBorder() {
        return border;
    }

    public Stock getStock() {
        return stock;
    }
}
